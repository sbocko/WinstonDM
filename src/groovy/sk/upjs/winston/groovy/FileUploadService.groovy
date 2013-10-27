package sk.upjs.winston.groovy

import org.codehaus.groovy.grails.web.context.ServletContextHolder;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author Stefan Bocko
 * This class uploads file from user to the appropriate directory on the server.
 *
 */
class FileUploadService {
	public static final DATASET_UPLOAD_DIRECTORY = "datasets"

	boolean transactional = true
	
	def String uploadFile(MultipartFile file, String name, String destinationDirectory) {
		
//		get storage path
		def servletContext = ServletContextHolder.servletContext
		def storagePath = servletContext.getRealPath(destinationDirectory)
		
//		Create storage path directory if it does not exist
		def storagePathDirectory = new File(storagePath)
		if(!storagePathDirectory.exists()){
			print "CREATING DIRECTORY ${storagePath}"
			if(storagePathDirectory.mkdirs()){
				println "SUCCESS"
			}else{
				println "FAILED"	
			}
		}
		
		println "storing file...";
		
//		Store file
		if(!file.isEmpty()){
			File uploadedFile = new File("${storagePath}/${name}")
			file.transferTo(uploadedFile)
			println "Saved file ${storagePath}/${name} - size: ${uploadedFile.size()}"
			
			return "${storagePath}/${name}"
		}else{
			println "File ${file} was empty!"
			return null
		}
	}
	
}
