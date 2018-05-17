/*
 * Developer: Flores López Angel Raymundo
 * Owlvirtech Inc.
 */
package clases;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author angelraymundo
 */
public class cToolBoxFlar {

    public cToolBoxFlar() {

    }

    public ArrayList<String> ParseArray(String[] arry) {
        ArrayList<String> arra = new ArrayList<>();
        for (int i = 0; i < arry.length; i++) {
            arra.add(arry[i]);
        }
        return arra;
    }

//    public String FileHandling(HttpServletRequest request,ServletContext servletContext) {
//        String response = "";
//        File file;
//        int maxFileSize = 5000 * 1024;
//        int maxMemSize = 5000 * 1024;
//        
//        String filePath = servletContext.getInitParameter("rutaArchivos");
//
//        // Verify the content type
//        String contentType = request.getContentType();
//
//        if ((contentType.indexOf("multipart/form-data") >= 0)) {
//
//            DiskFileItemFactory factory;
//            factory = new DiskFileItemFactory();
//            // maximum size that will be stored in memory
//            factory.setSizeThreshold(maxMemSize);
//            // Location to save data that is larger than maxMemSize.
//            factory.setRepository(new File("C:/Extras/subirArchivo/src/java"));
//
//            // Create a new file upload handler
//            ServletFileUpload upload;
//            upload = new ServletFileUpload(factory);
//            // maximum file size to be uploaded.
//            upload.setSizeMax(maxFileSize);
//            try {
//                // Parse the request to get file items.
//                List fileItems = upload.parseRequest(request);
//                // Process the uploaded file items
//                Iterator i;
//                i = fileItems.iterator();
//
//                while (i.hasNext()) {
//                    FileItem fi;
//                    fi = (FileItem) i.next();
//                    if (!fi.isFormField()) {
//                        // Get the uploaded file parameters
//                        String fieldName = fi.getFieldName();
//                        String fileName = fi.getName();
//                        boolean isInMemory = fi.isInMemory();
//                        long sizeInBytes = fi.getSize();
//
//                        // Write the file
//                        if (fileName.lastIndexOf("\\") >= 0) {
//                            file = new File(filePath
//                                    + fileName.substring(fileName.lastIndexOf("\\")));
//                        } else {
//                            file = new File(filePath
//                                    + fileName.substring(fileName.lastIndexOf("\\") + 1));
//                        }
//                        fi.write(file);
//                        response = filePath + fileName ;
//                    }
//                }
//
//            } catch (Exception ex) {
//                System.out.println(ex);
//            }
//        } else {
//
//            response = "No file uploaded";
//
//        }
//        return response;
//    }
}
