package com.base.production;

import java.io.File;
import java.sql.Connection;

import com.base.production.domain.Table;
import com.base.production.factory.BaseFactory;
import com.base.production.glocal.ClassNames;
import com.base.production.glocal.GlobalParam;
import com.base.production.jdbc.JDBCHandle;
import com.base.production.jdbc.JDBCTemplate;
import com.base.production.tools.AssistTools;

public class Production {
	static final String webroot = "webapp";

	public static void main(String[] args) throws Exception {
		ClassNames[] classNameses = { ClassNames.DAO, ClassNames.CONTROLLER, ClassNames.SQL, ClassNames.MODEL, ClassNames.SERVICE, ClassNames.JSP_INDEX, ClassNames.JSP_ADD,
				ClassNames.JSP_UPDATE };

		JDBCTemplate.DRIVERCLASS = "com.mysql.jdbc.Driver";
		JDBCTemplate.URL = "jdbc:mysql://114.113.238.50:43306/hxt_dg2";
		JDBCTemplate.USERNAME = "root";
		JDBCTemplate.PASSWORD = "xtgjfge";
		
		String projectRoot = "G:\\demo\\hxt_dg";
		String domainName = "hMessagePhone";
		String tableName = "h_message_phone";
		String packName = "com.hxt";
		String description = "@author	wuzhenwei";
		String encoding = "UTF-8";

		operater(classNameses, tableName, projectRoot, domainName, packName, null, description, encoding);

		ClassNames[] movePath = { ClassNames.JSP_ADD, ClassNames.JSP_INDEX, ClassNames.JSP_UPDATE };
		copyFile(movePath, packName, projectRoot, domainName);

//		 Thread.sleep(2000);
//		 delete(packName, projectRoot, "page", domainName);
	}

	public static void copyFile(ClassNames[] classNameses, String packName, String projectRoot, String domainName) {
		for (ClassNames classNames : classNameses) {
			String sourcePath = projectRoot + File.separator + "src" + File.separator + packName.replace(".", File.separator) + File.separator + domainName + File.separator
					+ classNames.getPakName();
			String toFilePath = projectRoot + File.separator + webroot + File.separator + "WEB-INF" + File.separator + classNames.getPakName() + File.separator + domainName;
			System.out.println("toFilePath:"+toFilePath);
			AssistTools.copyFile(new File(sourcePath), new File(toFilePath));
		}
	}

	public static void delete(String packName, String projectRoot, String directory, String domainName) {
		String sourcePath = projectRoot + File.separator + "src" + File.separator + packName.replace(".", File.separator) + File.separator + domainName + File.separator
				+ directory;
		System.out.println(sourcePath);
		AssistTools.deleteAllFile(new File(sourcePath));
	}

	public static void operater(ClassNames[] classNameses, String tableName, String projectRoot, String domainName, String packName, String templateDirectory, String description,
			String encoding) throws Exception {

		for (ClassNames classNames : classNameses) {
			Connection connection = JDBCTemplate.openConnection();

			ClassNames names = classNames;

			String packNames = packName + File.separator + domainName;

			String pak = names.getPakName();

			Table table = new Table();
			table.setTableName(tableName);
			table = JDBCHandle.getColumns(table, connection);

			table.setDescription(description);
			table.setPackageName(packNames.replace(File.separator, "."));
			table.setDomainName(domainName);
			table.setEncoding(encoding);
			table.setProjectRoot(projectRoot);
			table.setTemplateDirectory(templateDirectory);
			table.setTemplateName(GlobalParam.getTemplateName(names.getVal()));

			String filePrex = AssistTools.toUppercase(domainName);
			if (names.getMethodName().endsWith("jsp")) {
				filePrex = domainName;
			}
			String outFilePath = projectRoot + File.separator + "src" + File.separator + packNames.replace(".", File.separator) + File.separator + pak + File.separator + filePrex
					+ AssistTools.toUppercase(names.getMethodName());

			table.setOutFilePath(outFilePath);
			table.setPak(pak);

			table.setConnection(connection);

			BaseFactory actionFactory = new BaseFactory();
			actionFactory.create(table);
		}
	}

}
