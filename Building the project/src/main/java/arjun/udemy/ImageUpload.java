package arjun.udemy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import arjun.udemy.hibernate.DAO.FilesDAO;
import arjun.udemy.hibernate.entity.Files;

@WebServlet("/ImageUpload")
public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String path="C:/images/";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		switch (action) {
		case "filesUpload": 
			filesUpload(request, response);
			break;
		case "updateInfo": 
			updateInfo(request, response);
			break;
		default:
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action=request.getParameter("action");
		switch (action) {
		case "listingImages": 
			listingImages(request, response);
			break;
		case "viewImage": 
			viewImage(request, response);
			break;
		case "deleteImage": 
			deleteImage(request, response);
			break;
		default:
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	private void deleteImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int fileId=Integer.parseInt(request.getParameter("fileId"));
		Files file=new FilesDAO().getFile(fileId);
		new FilesDAO().deleteFile(fileId);
		//logic for deletion from filesystem
		File fileonDisc=new File(path+file.getFilename());
		if(fileonDisc.delete()) {
			System.out.println("File got deleted from filesystem");
		}
		listingImages(request, response);
	}
	private void viewImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int fileId=Integer.parseInt(request.getParameter("fileId"));
		Files file=new FilesDAO().getFile(fileId);
		System.out.println(file);
		request.setAttribute("file", file);
		request.setAttribute("path", path);
		request.getRequestDispatcher("viewimage.jsp").forward(request, response);
	}
	private void updateInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int fileId=Integer.parseInt(request.getParameter("fileId"));
		String label=request.getParameter("label");
		String caption=request.getParameter("caption");
		String fileName=request.getParameter("fileName");
		Files file=new Files(fileId, fileName, label, caption);
		new FilesDAO().updateInfo(fileId,label,caption);
		listingImages(request, response);
		
	}
	private void listingImages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Files> files=new FilesDAO().listFiles();
		request.setAttribute("files", files);
		request.setAttribute("path", path);
		request.getRequestDispatcher("listFiles.jsp").forward(request, response);
	}
	public void filesUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ServletFileUpload upload =new ServletFileUpload(new DiskFileItemFactory());
		List<FileItem> images;
		try {
			images = upload.parseRequest(request);
			for(FileItem image:images ) {
				String name=image.getName();
				File file=new File(path+name);
				if(!file.exists()) {
					image.write(file);
					new FilesDAO().addFileDetail(new Files(name));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listingImages(request, response);
	}
}
