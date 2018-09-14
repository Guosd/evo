package org.kyll.demo.infa;

import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-09-02 13:01
 */
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class EvoSpDemoInfaApplication {
	private static final String infaDoc = "C:\\Users\\Administrator\\OneDrive\\工作\\中科软\\接口文档\\P2P接口文档201808301802_20180901new.docx";
	private static final String codeSrc = "C:\\Users\\Administrator\\IdeaProjects\\sp-eco-mobile-p2p\\sp-eco-mobile-p2p-core\\src\\main\\java\\com\\yqjr\\mobile\\p2p";
	private static final String packageName = "com.yqjr.mobile.p2p";

	@Data
	private static class FieldDesc {
		private String cname;
		private String ename;
		private String type;
		private String comment;
	}

	@Data
	private static class DtoDesc {
		private String className;
		private String classComment;
		private List<FieldDesc> fieldDescList;
		private boolean subDto;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EvoSpDemoInfaApplication.class, args);

		XWPFDocument document = new XWPFDocument(new FileInputStream(infaDoc));

		List<DtoDesc> dtoDescList = new ArrayList<>();

		List<String> infaNameList = new ArrayList<>();
		String prefixClassName = null;
		List<XWPFTable> tableList = document.getTables();
		for (int i = 2, size =  tableList.size() - 2; i < size; i++) {
			XWPFTable table = tableList.get(i);

			DtoDesc dtoDesc = new DtoDesc();

			List<FieldDesc> fieldDescList = new ArrayList<>();
			for (int j = 1, numberOfRows = table.getNumberOfRows(); j < numberOfRows; j++) {
				XWPFTableRow tableRow = table.getRow(j);
				FieldDesc fieldDesc = new FieldDesc();
				fieldDesc.setCname(tableRow.getCell(0).getText().trim());
				fieldDesc.setEname(tableRow.getCell(1).getText().trim());
				fieldDesc.setType(tableRow.getCell(2).getText().trim());
				fieldDesc.setComment(tableRow.getCell(3).getText().trim());
				fieldDescList.add(fieldDesc);
			}
			dtoDesc.setFieldDescList(fieldDescList);


			if (i % 2 == 0) {
				String infaName = getInfaName(fieldDescList);
				infaNameList.add(infaName);

				prefixClassName = StringUtil.toCapture(infaName);
				dtoDesc.setClassName(prefixClassName + "ReqDto");
			} else {
				dtoDesc.setClassName(prefixClassName + "ResDto");
			}

			dtoDescList.add(dtoDesc);
		}

		List<String> infaDescList = new ArrayList<>();
		for (XWPFParagraph paragraph : document.getParagraphs()) {
			if ("%1.".equals(paragraph.getNumLevelText())) {
				infaDescList.add(paragraph.getText());
			}
		}
		infaDescList.remove(infaDescList.size() - 1);

		int count = 0;
		for (String infaDesc : infaDescList) {
			dtoDescList.get(count++).setClassComment(infaDesc + "请求对象");
			dtoDescList.get(count++).setClassComment(infaDesc + "响应对象");
		}

		List<DtoDesc> subDtoDescList = new ArrayList<>();
		for (DtoDesc dtoDesc : dtoDescList) {
			List<FieldDesc> listFieldDescList = getListFieldDesc(dtoDesc);

			if (listFieldDescList.isEmpty()) {
				continue;
			}

			List<DtoDesc> tempSubDtoDescList = new ArrayList<>();
			List<FieldDesc> tempFieldDescList = new ArrayList<>();
			for (FieldDesc listFieldDesc : listFieldDescList) {
				String listEname = listFieldDesc.getEname();

				DtoDesc tempSubDtoDesc = new DtoDesc();
				tempSubDtoDesc.setClassName(listEname);
				tempSubDtoDesc.setClassComment(listFieldDesc.getComment());

				List<FieldDesc> subFieldDescList = new ArrayList<>();
				for (FieldDesc fieldDesc : dtoDesc.getFieldDescList()) {
					String ename = fieldDesc.getEname();
					if (ename.startsWith(listEname)) {
						if (!ename.equals(listEname)) {
							FieldDesc subFieldDesc = new FieldDesc();
							subFieldDesc.setCname(fieldDesc.getCname());
							subFieldDesc.setEname(ename.split("\\.")[1].trim());
							subFieldDesc.setType(fieldDesc.getType());
							subFieldDesc.setComment(fieldDesc.getComment());
							subFieldDescList.add(subFieldDesc);
						}
					} else {
						boolean exist = false;
						for (FieldDesc tempListFieldDesc : listFieldDescList) {
							if (ename.startsWith(tempListFieldDesc.getEname())) {
								exist = true;
								break;
							}
						}

						if (!exist) {
							tempFieldDescList.add(fieldDesc);
						}
					}
				}
				tempSubDtoDesc.setFieldDescList(subFieldDescList);

				tempSubDtoDescList.add(tempSubDtoDesc);
			}

			List<FieldDesc> fieldDescList = new ArrayList<>(tempFieldDescList);
			for (DtoDesc tempSubDtoDesc : tempSubDtoDescList) {
				String type = dtoDesc.getClassName().replace("ReqDto", "").replace("ResDto", "") + StringUtil.toCapture(tempSubDtoDesc.getClassName().replace("list", "").replace("List", "")) + (dtoDesc.getClassName().endsWith("ReqDto") ? "ReqDto" : "ResDto");
				FieldDesc fieldDesc = new FieldDesc();
				fieldDesc.setCname(tempSubDtoDesc.getClassComment());
				fieldDesc.setEname(tempSubDtoDesc.getClassName());
				fieldDesc.setType("List<" + type + ">");
				fieldDesc.setComment("");
				fieldDescList.add(fieldDesc);

				DtoDesc subDtoDesc = new DtoDesc();
				subDtoDesc.setClassName(type);
				subDtoDesc.setClassComment(tempSubDtoDesc.getClassComment());
				subDtoDesc.setFieldDescList(tempSubDtoDesc.getFieldDescList());
				subDtoDesc.setSubDto(true);
				subDtoDescList.add(subDtoDesc);
			}
			dtoDesc.setFieldDescList(fieldDescList);
		}

		System.out.println("out sub dto");
		outDto(infaNameList, infaDescList, subDtoDescList);
		System.out.println("out dto");
		outDto(infaNameList, infaDescList, dtoDescList);
		System.out.println("out api");
		outApi(infaNameList, infaDescList, dtoDescList);
		System.out.println("out bizz");
		outBizz(infaNameList, infaDescList, dtoDescList);
		System.out.println("out rest");
		outRest(infaNameList, infaDescList, dtoDescList);
		System.out.println("finish");
	}

	private static void outRest(List<String> infaNameList, List<String> infaDescList, List<DtoDesc> dtoDescList) throws Exception {
		PrintWriter out = new PrintWriter(new FileWriter(codeSrc + "\\rest\\P2PRest.java"));
		out.println("package " + packageName + ".rest;");
		out.println();
		out.println("import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;");
		out.println();
		out.println("import lombok.extern.slf4j.Slf4j;");
		out.println("import " + packageName + ".bizz.P2PBizz;");

		for (int i = 0, size = infaNameList.size(); i < size; i++) {
			out.println("import " + packageName + ".model." + dtoDescList.get(i * 2).getClassName() + ";");
			out.println("import " + packageName + ".model." + dtoDescList.get(i * 2 + 1).getClassName() + ";");
		}

		out.println("import " + packageName + ".model.base.P2PResDto;");
		out.println("import org.springframework.beans.factory.annotation.Autowired;");
		out.println("import org.springframework.web.bind.annotation.PostMapping;");
		out.println("import org.springframework.web.bind.annotation.RequestBody;");
		out.println("import org.springframework.web.bind.annotation.RequestMapping;");
		out.println("import org.springframework.web.bind.annotation.RestController;");
		out.println();
		out.println("@Slf4j");
		out.println("@RequestMapping(\"/p2p\")");
		out.println("@RestController");
		out.println("public class P2PRest {");
		out.println("\t@Autowired");
		out.println("\tprivate P2PBizz p2pBizz;");
		out.println();

		for (int i = 0, size = infaNameList.size(); i < size; i++) {
			out.println("\t@PostMapping(\"/" + infaNameList.get(i) + "\")");
			out.println("\tpublic ServiceResponse<" + dtoDescList.get(i * 2 + 1).getClassName() + "> " + infaNameList.get(i) + "(@RequestBody " + dtoDescList.get(i * 2).getClassName() + " " + infaNameList.get(i) + "ReqDto) {");
			out.println("\t\tP2PResDto<" + dtoDescList.get(i * 2 + 1).getClassName() + "> p2pResDto = p2pBizz." + infaNameList.get(i) + "(" + infaNameList.get(i) + "ReqDto);");
			out.println("\t\treturn ServiceResponse.of(p2pResDto.getStatus(), p2pResDto.getMsg(), p2pResDto.getData());");
			out.println("\t}");
			out.println();
		}

		out.println("}");
		out.close();
	}

	private static void outBizz(List<String> infaNameList, List<String> infaDescList, List<DtoDesc> dtoDescList) throws Exception {
		PrintWriter out = new PrintWriter(new FileWriter(codeSrc + "\\bizz\\P2PBizz.java"));
		out.println("package " + packageName + ".bizz;");
		out.println();
		out.println("import lombok.extern.slf4j.Slf4j;");
		out.println("import " + packageName + ".api.P2PInfaApi;");

		for (int i = 0, size = infaNameList.size(); i < size; i++) {
			out.println("import " + packageName + ".model." + dtoDescList.get(i * 2).getClassName() + ";");
			out.println("import " + packageName + ".model." + dtoDescList.get(i * 2 + 1).getClassName() + ";");
		}

		out.println("import " + packageName + ".model.base.P2PResDto;");
		out.println("import org.springframework.beans.factory.annotation.Autowired;");
		out.println("import org.springframework.stereotype.Service;");
		out.println();
		out.println("@Slf4j");
		out.println("@Service");
		out.println("public class P2PBizz {");
		out.println("\t@Autowired");
		out.println("\tprivate P2PInfaApi p2pInfaApi;");
		out.println();

		for (int i = 0, size = infaNameList.size(); i < size; i++) {
			out.println("\tpublic P2PResDto<" + dtoDescList.get(i * 2 + 1).getClassName() + "> " + infaNameList.get(i) + "(" + dtoDescList.get(i * 2).getClassName() + " " + infaNameList.get(i) + "ReqDto) {");
			out.println("\t\tlog.info(\"调用P2P接口: \" + " + infaNameList.get(i) + "ReqDto);");
			out.println("\t\treturn p2pInfaApi." + infaNameList.get(i) + "(" + infaNameList.get(i) + "ReqDto);");
			out.println("\t}");
			out.println();
		}

		out.println("}");
		out.close();
	}

	private static void outApi(List<String> infaNameList, List<String> infaDescList, List<DtoDesc> dtoDescList) throws Exception {
		PrintWriter out = new PrintWriter(new FileWriter(codeSrc + "\\api\\P2PInfaApi.java"));
		out.println("package " + packageName + ".api;");
		out.println();

		for (int i = 0, size = infaNameList.size(); i < size; i++) {
			out.println("import " + packageName + ".model." + dtoDescList.get(i * 2).getClassName() + ";");
			out.println("import " + packageName + ".model." + dtoDescList.get(i * 2 + 1).getClassName() + ";");
		}

		out.println("import " + packageName + ".model.base.P2PReqDto;");
		out.println("import " + packageName + ".model.base.P2PResDto;");
		out.println("import org.springframework.cloud.openfeign.FeignClient;");
		out.println("import org.springframework.web.bind.annotation.PostMapping;");
		out.println();
		out.println("@FeignClient(url = \"\", name = \"p2p\")");
		out.println("public interface P2PInfaApi {");

		for (int i = 0, size = infaNameList.size(); i < size; i++) {
			out.println("\t@PostMapping");
			out.println("\tP2PResDto<" + dtoDescList.get(i * 2 + 1).getClassName() + "> " + infaNameList.get(i) + "(" + dtoDescList.get(i * 2).getClassName() + " " + infaNameList.get(i) + "ReqDto);");
			out.println();
		}

		out.println("}");
		out.close();
	}

	private static void outDto(List<String> infaNameList, List<String> infaDescList, List<DtoDesc> dtoDescList) throws Exception {
		for (DtoDesc dtoDesc : dtoDescList) {
			if (dtoDesc.getClassName().contains("GetQuestions")) {
				continue;
			}

			PrintWriter out = new PrintWriter(new FileWriter(codeSrc + "\\model\\" + dtoDesc.getClassName() + ".java"));
			out.println("package " + packageName + ".model;");
			out.println();
			out.println("import lombok.Data;");

			if (dtoDesc.getClassName().endsWith("ReqDto")) {
				out.println("import lombok.EqualsAndHashCode;");
				out.println("import " + packageName + ".model.base.P2PReqDto;");
			}

			if (hasList(dtoDesc)) {
				out.println();
				out.println("import java.util.List;");
			}

			out.println();
			out.println("/**");
			out.println(" * " + dtoDesc.getClassComment());
			out.println(" */");

			if (dtoDesc.getClassName().endsWith("ReqDto")) {
				out.println("@EqualsAndHashCode(callSuper = true)");
			}

			out.println("@Data");

			if (dtoDesc.getClassName().endsWith("ReqDto")) {
				out.println("public class " + dtoDesc.getClassName() + " extends P2PReqDto {");
			} else {
				out.println("public class " + dtoDesc.getClassName() + " {");
			}


			for (FieldDesc fieldDesc : dtoDesc.getFieldDescList()) {
				if (!dtoDesc.isSubDto() && ((dtoDesc.getClassName().endsWith("ReqDto") && "funcode".equals(fieldDesc.getEname()))
						|| (dtoDesc.getClassName().endsWith("ResDto") && ("status".equals(fieldDesc.getEname()) || "msg".equals(fieldDesc.getEname()))))) {
					continue;
				}

				if (StringUtil.isBlank(fieldDesc.getEname())) {
					System.out.println("属性为空: " + fieldDesc);
				} else {
					out.println("\tprivate " + convertType(dtoDesc, fieldDesc) + " " + fieldDesc.getEname() + ";// " + fieldDesc.getCname() + " " + fieldDesc.getComment());
				}
			}

			out.println("}");
			out.close();
		}
	}

	private static String getInfaName(List<FieldDesc> fieldDescList) {
		String comment = null;
		for (FieldDesc fieldDesc : fieldDescList) {
			if ("funcode".equalsIgnoreCase(fieldDesc.getCname()) || "funcode".equalsIgnoreCase(fieldDesc.getEname())) {
				comment = fieldDesc.getComment();
				break;
			}
		}

		String result = null;
		if (StringUtil.isNotBlank(comment)) {
			int pos = -1;
			char[] cs = comment.toCharArray();
			for (int i = 0, length = cs.length; i < length; i++) {
				if (isEnglish(cs[i])) {
					pos = i;
					break;
				}
			}

			if (pos > -1) {
				result = comment.substring(pos);
			}
		}

		return result;
	}

	private static List<FieldDesc> getListFieldDesc(DtoDesc dtoDesc) {
		List<FieldDesc> fieldDescList = new ArrayList<>();
		for (FieldDesc fieldDesc : dtoDesc.getFieldDescList()) {
			if ("List".equals(convertType(dtoDesc, fieldDesc))) {
				fieldDescList.add(fieldDesc);
			}
		}
		return fieldDescList;
	}

	private static String convertType(DtoDesc dtoDesc, FieldDesc fieldDesc) {
		String type = fieldDesc.getType();
		if ("GetCityiesReqDto".equals(dtoDesc.getClassName()) && "cityName".equals(fieldDesc.getEname())) {
			type = "String";
		}

		String result = null;
		if (StringUtil.isNotBlank(type)) {
			int pos = type.indexOf('(');
			if (pos > -1) {
				result = type.substring(0, pos);
			} else {
				result = type;
			}

			if ("int".equalsIgnoreCase(result)) {
				result = "Integer";
			} else if ("long".equalsIgnoreCase(result)) {
				result = "Long";
			} else if ("double".equalsIgnoreCase(result)) {
				result = "Double";
			} else if ("string".equalsIgnoreCase(result) || "Stirng".equalsIgnoreCase(result)) {
				result = "String";
			} else if (result.toLowerCase().startsWith("list")) {
				if (result.toLowerCase().contains("<object>")) {
					result = "List";
				}
			}
		}
		return result;
	}

	private static String convertType(String type) {
		String result = null;
		if (StringUtil.isNotBlank(type)) {
			int pos = type.indexOf('(');
			if (pos > -1) {
				result = type.substring(0, pos);
			} else {
				result = type;
			}

			if (result.toLowerCase().startsWith("list")) {
				result = "List";
			}
		}
		return result;
	}

	private static boolean hasList(DtoDesc dtoDesc) {
		for (FieldDesc fieldDesc : dtoDesc.getFieldDescList()) {
			if ("List".equals(convertType(fieldDesc.getType()))) {
				return true;
			}
		}
		return false;
	}

	private static boolean isEnglish(char c) {
		return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
	}
}
