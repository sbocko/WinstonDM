import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.commons.CommonsMultipartFile

import winston.*

class BootStrap {
	//depencency injection
	DatasetService datasetService
	def grailsApplication
	
    def init = { servletContext ->
		
		def resourceFile = grailsApplication.mainContext.getResource('/WEB-INF/resources/poker-hand-testing.data').file
//		File file = new File("/Volumes/Seagate HDD/datasets/poker-hand-testing.data")
		datasetService.saveDataset("Iris", "Iris dataset from UCI", resourceFile, null)
		
//    	def dataset1 = new Dataset(title : 'Wine data set', 
//    								dataFile: 'wine.data', 
//    								description: '''These data are the results of a chemical analysis of wines grown in the same region 
//    												in Italy but derived from three different cultivars. The analysis determined the 
//    												quantities of 13 constituents found in each of the three types of wines.''', 
//									descriptionFile: 'wine.names',
//									missingValuePattern : null,
//									numberOfMissingValues: 0,
//									numberOfInstances: 178
//									)
//    	if(!dataset1.save()){
//    		dataset1.errors.allErrors.each{ error ->
//    			println "an error occured with dataset1: ${error}"
//    		}
//	    }
//
//	    def dataset2 = new Dataset(title : 'Abalone data set', 
//    								dataFile: 'abalone.data', 
//    								description: '''Predicting the age of abalone from physical measurements. The age of abalone is
//    												 determined by cutting the shell through the cone, staining it, and counting the
//    												 number of rings through a microscope -- a boring and time-consuming task.''', 
//									descriptionFile: 'abalone.names',
//									missingValuePattern : null,
//									numberOfMissingValues: 0,
//									numberOfInstances: 4177
//									)
//    	if(!dataset2.save()){
//    		dataset2.errors.allErrors.each{ error ->
//    			println "an error occured with dataset2: ${error}"
//    		}
//	    }
//
//        def wineAttr1 = new Attribute(title: 'class', attributeType: 'DISCRETE', numberOfMissingValues: 0)
//        def wineAttr2 = new Attribute(title: 'Alcohol', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def wineAttr3 = new Attribute(title: 'Malic acid', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def wineAttr4 = new Attribute(title: 'Ash', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def wineAttr5 = new Attribute(title: 'Alcalinity of ash', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//
//        def wineAttr6 = new Attribute(title: 'Magnesium', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def wineAttr7 = new Attribute(title: 'Total phenols', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def wineAttr8 = new Attribute(title: 'Flavanoids', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def wineAttr9 = new Attribute(title: 'Nonflavanoid phenols', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def wineAttr10 = new Attribute(title: 'Proanthocyanins', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//
//        def wineAttr11 = new Attribute(title: 'Color intensity', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def wineAttr12 = new Attribute(title: 'Hue', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def wineAttr13 = new Attribute(title: 'OD280/OD315 of diluted wines', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def wineAttr14 = new Attribute(title: 'Proline', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//
//        wineAttr1.save()
//        wineAttr2.save()
//        wineAttr3.save()
//        wineAttr4.save()
//        wineAttr5.save()
//
//        wineAttr6.save()
//        wineAttr7.save()
//        wineAttr8.save()
//        wineAttr9.save()
//        wineAttr10.save()
//
//        wineAttr11.save()
//        wineAttr12.save()
//        wineAttr13.save()
//        wineAttr14.save()
//
//        dataset1.addToAttributes(wineAttr1)
//        dataset1.addToAttributes(wineAttr2)
//        dataset1.addToAttributes(wineAttr3)
//        dataset1.addToAttributes(wineAttr4)
//        dataset1.addToAttributes(wineAttr5)
//
//        dataset1.addToAttributes(wineAttr6)
//        dataset1.addToAttributes(wineAttr7)
//        dataset1.addToAttributes(wineAttr8)
//        dataset1.addToAttributes(wineAttr9)
//        dataset1.addToAttributes(wineAttr10)
//
//        dataset1.addToAttributes(wineAttr11)
//        dataset1.addToAttributes(wineAttr12)
//        dataset1.addToAttributes(wineAttr13)
//        dataset1.addToAttributes(wineAttr14)
//
//        def abaloneAttr1 = new Attribute(title: 'Sex', attributeType: 'DISCRETE', numberOfMissingValues: 0)
//        def abaloneAttr2 = new Attribute(title: 'Length', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def abaloneAttr3 = new Attribute(title: 'Diameter', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def abaloneAttr4 = new Attribute(title: 'Height', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def abaloneAttr5 = new Attribute(title: 'Whole weigt', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//
//        def abaloneAttr6 = new Attribute(title: 'Shucked weight', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def abaloneAttr7 = new Attribute(title: 'Viscera weight', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def abaloneAttr8 = new Attribute(title: 'Shell weight', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//        def abaloneAttr9 = new Attribute(title: 'Rings', attributeType: 'NUMERIC', numberOfMissingValues: 0)
//
//        dataset2.addToAttributes(abaloneAttr1)
//        dataset2.addToAttributes(abaloneAttr2)
//        dataset2.addToAttributes(abaloneAttr3)
//        dataset2.addToAttributes(abaloneAttr4)
//        dataset2.addToAttributes(abaloneAttr5)
//
//        dataset2.addToAttributes(abaloneAttr6)
//        dataset2.addToAttributes(abaloneAttr7)
//        dataset2.addToAttributes(abaloneAttr8)
//        dataset2.addToAttributes(abaloneAttr9)
	}

    def destroy = {
    }
}