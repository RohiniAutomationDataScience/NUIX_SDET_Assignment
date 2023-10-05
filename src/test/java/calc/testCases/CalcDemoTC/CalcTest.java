package calc.testCases.CalcDemoTC;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import calc.utility.base.BaseTestClass;
import calc.wspages.calcDemo.WebCalcLoad;


public class CalcTest  extends BaseTestClass {
	WebCalcLoad wc = new WebCalcLoad () ;


	@DataProvider(name="getData")
	public static String[][] getDataFromSheet() throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook(System.getProperty("user.dir") + "/" + "TestData.xlsx");
		XSSFSheet workSheet = workbook.getSheet("Sheet1");
		int noOfRows = workSheet.getLastRowNum() + 1;
		int noOfColumns = workSheet.getRow(0).getLastCellNum();
		String[][] dataTable = new String[noOfRows][noOfColumns];

		for (int i = workSheet.getFirstRowNum(); i < workSheet.getLastRowNum() + 1; i++) {
			Row row = workSheet.getRow(i);
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				DataFormatter df = new DataFormatter();
				dataTable[i][j]  = df.formatCellValue(cell);

			}
		}

		workbook.close();
		return dataTable;
	}


	@Test(dataProvider="getData")
	public void VerifyCalcOperations(String Operation, Object num1, Object num2, Object ExpectedResult) throws Exception{
		wc.LaunchApplication();
		Double actualTest = WebCalcLoad.validateOperations(Operation, num1, num2);
		wc.validateResult(actualTest,ExpectedResult);
	}



}



