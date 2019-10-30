package com.framework.meteor.framework.controller;


import com.framework.meteor.framework.util.DateUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;

/**
 * @author Meteor.wu
 * @since 2018/6/20 11:46
 */

@Controller
@RequestMapping("/upload")
public class UploadController {

    @Value("${upload.file.baseUrl}")
    private String baseUrl;

    @RequestMapping("/picture")
    public void uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 判断存放上传文件的目录是否存在（不存在则创建）
        String dateFileName = DateUtils.getDateString(Calendar.getInstance().getTime(), DateUtils.PATTERN_DATE_NO_DAY);
        String path = baseUrl + "/" + dateFileName + "/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }


        request.setCharacterEncoding("utf-8"); //设置编码
        JSONObject ret = new JSONObject();
        try {
            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            Iterator<String> iterator = req.getFileNames();
            while (iterator.hasNext()) {
//                HashMap<String, Object> res = new HashMap<String, Object>();
                MultipartFile file = req.getFile(iterator.next());
                // 获取文件名
                String fileNames = file.getOriginalFilename();
                int split = fileNames.lastIndexOf(".");
                //获取上传文件的后缀
                String extName = fileNames.substring(split + 1, fileNames.length());
                //申明UUID
                String uuid = UUID.randomUUID().toString().replace("-", "");

                //组成新的图片名称
                String newName = uuid + "." + extName;
                System.out.println(newName);
                ret.put("url", dateFileName + "&" + newName);

                System.out.println(ret.getString("url"));

                String destPath = path + newName;
                System.out.println("destPath=" + destPath);

                //真正写到磁盘上
                File file1 = new File(destPath);
                OutputStream out = new FileOutputStream(file1);
                out.write(file.getBytes());

                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        printWriter.write(ret.toString());
        printWriter.flush();

    }

    /**
     * 图片回显
     * @param response
     */
    @GetMapping(value="showPic/{filePath}")
    public void showPic(HttpServletResponse response, @PathVariable String filePath){
        String base_dir = baseUrl + "/" +  filePath.replace("&","/");  //得到文件上传的服务器路径
        base_dir = formatPath(base_dir);
        FileInputStream in = null;
        OutputStream os = null;
        try {
            in = new FileInputStream(base_dir);
            response.setContentType("image/jpeg");
            os = response.getOutputStream();
            if(in != null && in.available() > 0){
                IOUtils.copy(in, os);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(os != null ){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 格式化系统中的路径  转换 / 和 \
     * @param path
     * @return
     */
    public static String formatPath(String path){
        if(StringUtils.isNotBlank(path)){
            path = path.replaceAll("\\||\\\\", "/");
        }
        return path;
    }
}
