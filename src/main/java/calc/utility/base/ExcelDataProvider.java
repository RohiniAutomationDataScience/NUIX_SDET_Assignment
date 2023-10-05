package calc.utility.base;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;




public class ExcelDataProvider {

	private static Workbook workbook = null;

	/**
	 * @param File Name
	 * @param Sheet Name
	 * @return
	 * @throws Exception 
	 */

	

	/*
	 * // @DataProvider(name="getData") public Object[][] loginData() { Object[][]
	 * arrayObject = null; try { arrayObject = getDataFromSheet(); } catch
	 * (Exception e) { e.printStackTrace(); } return arrayObject; }
	 */

	public static String getCellContentAsString(Cell cell) throws Exception {
		String celldata = "";
		switch (cell.getCellTypeEnum()) {
		case BLANK:
			celldata = "";
			break;

		case STRING:
			celldata = cell.getStringCellValue();
			break;

		case NUMERIC:
			DataFormatter df = new DataFormatter();
			celldata = df.formatCellValue(cell);
			break;

		case FORMULA:
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			celldata = evaluator.evaluate(cell).getStringValue();
			break;

		case BOOLEAN:
			celldata = String.valueOf(cell.getBooleanCellValue());
			break;

		default:
			celldata = cell.getStringCellValue();
			break;
		}
		return celldata;
	}
}
