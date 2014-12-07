import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.commons.CommonsMultipartFile

import com.sun.org.apache.bcel.internal.generic.RETURN;

import winston.*

class BootStrap {
	//depencency injection
	DatasetService datasetService
	def grailsApplication

	def init = { servletContext ->
//		def datasetList = Dataset.getAll()
//		def datasetNameList = datasetList.collect { entry -> entry.title }

		//		def resourceDir = grailsApplication.mainContext.getResource('/datasets').file
		def resourceDir = new File('/Volumes/Seagate HDD/datasets/')
		
		if(!resourceDir.exists()){
			println "resource dir does not exist"
			return;
		}

		resourceDir.eachFile { file ->
			processDataset(file, datasetNameList);
		}

		//		File file = new File("/Volumes/Seagate HDD/datasets/poker-hand-testing.data")
		//		datasetService.saveDataset("Iris", "Iris dataset from UCI", resourceFile, null)
	}

	private void processDataset(def file, def datasetNameList){
		def datasetName = file.name.substring(0,file.name.indexOf('.'))

		if(datasetNameList.contains(datasetName)){
			println "${datasetName} is already in DB"
			return
		}

		def missingValuePattern = null;

		switch (file.name) {
			case 'abalone.data':
			//no missing values
				break
			case 'ad.data':
				missingValuePattern = "?"
				break
			case 'adult+stretch.data':
			//no missing values
				break
			case 'adult-stretch.data':
			//no missing values
				break
			case 'adult.data':
				missingValuePattern = "?"
				break
			case 'agaricus-lepiota.data':
				missingValuePattern = "?"
				break
			case 'allbp.data':
				missingValuePattern = "?"
				break
			case 'allhyper.data':
				missingValuePattern = "?"
				break
			case 'allhypo.data':
				missingValuePattern= "?"
				break
			case 'allrep.data':
				missingValuePattern="?"
				break
			case 'analysis.data':
			//no missing values
				break
			case 'ann-test.data':
			//does not have data in correct format TODO change delimiter to comma
				return
			case 'ann-train.data':
			//does not have data in correct format TODO change delimiter to comma
				return
			case 'anneal.data':
				missingValuePattern = "?"
				break
			case 'anonymous-msweb.data':
			// TODO problem with missing attributes
				return
			case 'arrhythmia.data':
				missingValuePattern = "?"
				break
			case 'audiology.data':
			//does not use standard csv - used standardized dataset
				return
			case 'audiology.standardized.data':
				missingValuePattern = "?"
				break
			case 'auto-mpg.data':
				missingValuePattern = "?"
				break
			case 'auto-mpg.data-original':
			//does not have missing values
				break
			case 'backup-large.data':
			//no missing values
				break
			case 'badges.data':
			//does not have missing values TODO change delimiter to comma
				return
			case 'balance-scale.data':
			//does not have missing values
				break
			case 'bands.data':
			//error parsing data
				missingValuePattern = "?"
				return
			case 'bezdekIris.data':
				break
			case 'breast-cancer-wisconsin.data':
			//no missing values
				break
			case 'bridges.data.version1':
			//no missing values
				break
			case 'bridges.data.version2':
			//no missing values
				break
			case 'bupa.data':
			//no missing values
				break
			case 'CalIt2.data':
			//no missing values
				break
			case 'car.data':
			//no missing values
				break
			case 'census-income.data':
			//TODO no such property exception
				missingValuePattern = "?"
				return
			case 'clean1.data':
			//no missing values
				break
			case 'clean2.data':
			//no missing values
				break
			case 'cleveland.data':
			//does not contain data in correct format
				return
			case 'cmc.data':
			//no missing values
				break
			case 'CNAE-9.data':
			//no missing values
				break
			case 'communities.data':
			//no missing values
				break
			case 'connect-4.data':
			//no missing values
				break
			case 'covtype.data':
			//no missing values
				break
			case 'crx.data':
				missingValuePattern = "?"
				break
			case 'dermatology.data':
				missingValuePattern = "?"
				break
			case 'dis.data':
				missingValuePattern ="?"
				break
			case 'Dodgers.data':
			//no missing values
				break
			case 'echocardiogram.data':
			//problem parsing data TODO
				missingValuePattern = "?"
				return
			case 'ecoli.data':
			//does not contain data in correct format TODO change delimiter to comma (,)
				return
			case 'eighthr.data':
				missingValuePattern = "?"
				break
			case 'eval.data':
			//no missing values
				break
			case 'flag.data':
			//no missing values
				break
			case 'flare.data1':
			//				does not have data in correct format TODO change delimiter to comma
				return
			case 'flare.data2':
			//			does not have data in correct format TODO change delimiter to comma
				return
			case 'FOIL.data':
			// does not have appropriate data
				return
			case 'function-finding.data':
			//does not contain data in correct format
				return
			case 'german.data':
			//			does not have data in correct format TODO change delimiter to comma
				return
			case 'german.data-numeric':
			//no missing values
				break
			case 'glass.data':
			//no missing values
				break
			case 'gripper.data':
			//no missing values
				break
			case 'haberman.data':
			//no missing values
				break
			case 'hayes-roth.data':
			//no missing values
				break
			case 'hepatitis.data':
				missingValuePattern = "?"
				break
			case 'Hill_Valley_with_noise_Testing.data':
			//no missing values
				break
			case 'Hill_Valley_with_noise_Training.data':
			//no missing values
				break
			case 'Hill_Valley_without_noise_Testing.data':
			//no missing values
				break
			case 'Hill_Valley_without_noise_Training.data':
			//no missing values
				break
			case 'horse-colic.data':
			//does not have data in correct format TODO change delimiter to comma
				missingValuePattern="?"
				return
			case 'house-votes-84.data':
				missingValuePattern = "?"
				break
			case 'housing.data':
			//does not have data in correct format TODO change delimiter to comma
				break
			case 'hungarian.data':
			//does not contain data in correct format
				return
			case 'hypothyroid.data':
				missingValuePattern="?"
				break
			case 'imports-85.data':
				missingValuePattern = "?"
				break
			case 'ionosphere.data':
			//no missing values
				break
			case 'iris.data':
			//no missing values
				break
			case 'isolet1+2+3+4.data':
			//no missing values
				break
			case 'isolet5.data':
			//no missing values
				break
			case 'kddcup_full.data':
			//no missing values TODO OutOfMemoryError
				return
			case 'kddcup.data_10_percent':
			//no missing values
				break
			case 'kinship.data':
			//does not have data in a correct format
				return
			case 'kr-vs-kp.data':
			//no missing values
				break
			case 'krkopt.data':
			//no missing values
				break
			case 'lenses.data':
			//does not have data in a correct format
				return
			case 'letter-recognition.data':
			//no missing values
				break
			case 'long-beach-va.data':
			//does not contain data in correct format
				return
			case 'lp1.data':
			//does not contain appropriate data
				return
			case 'lp2.data':
			//does not contain appropriate data
				return
			case 'lp3.data':
			//does not contain appropriate data
				return
			case 'lp4.data':
			//does not contain appropriate data
				return
			case 'lp5.data':
			//does not contain appropriate data
				return
			case 'lrs.data':
			//does not have data in correct format, TODO change delimiter to comma
				return
			case 'lung-cancer.data':
				missingValuePattern = "?"
				break
			case 'machine.data':
			//no missing values
				break
			case 'magic04.data':
			//no missing values
				break
			case 'mammographic_masses.data':
				missingValuePattern = "?"
				break
			case 'meta.data':
				missingValuePattern="?"
				break
			case 'moral.data':
			//does not have appropriate data
				return
			case 'move.data':
			//no missing values
				break
			case 'movement_libras.data':
			//no missing values
				break
			case 'movement_libras_1.data':
			//no missing values
				break
			case 'movement_libras_10.data':
			//no missing values
				break
			case 'movement_libras_5.data':
			//no missing values
				break
			case 'movement_libras_8.data':
			//no missing values
				break
			case 'movement_libras_9.data':
			//no missing values
				break
			case 'nettalk.data':
			//does not contain data in correct format
				return
			case 'new-thyroid.data':
			//no missing values
				break
			case 'new.data':
			//does not contain data in correct format
				return
			case 'nursery.data':
			//no missing values
				break
			case 'o-ring-erosion-only.data':
			//no missing values
				break
			case 'o-ring-erosion-or-blowby.data':
			//no missing values
				break
			case 'onehr.data':
				missingValuePattern = "?"
				break
			case 'original.data':
				break
			case 'page-blocks.data':
			//no missing values
				break
			case 'parkinsons.data':
			//no missing values
				break
			case 'parkinsons_updrs.data':
			//no missing values
				break
			case 'pima-indians-diabetes.data':
			//no missing values
				break
			case 'plantCellSignaling.data':
			//no missing values
				break
			case 'plants.data':
			//does not have appropriate data
				return
			case 'poker-hand-testing.data':
			//no missing values
				break
			case 'poker-hand-training-true.data':
			//no missing values
				break
			case 'post-operative.data':
				missingValuePattern = "?"
				break
			case 'processed.cleveland.data':
				missingValuePattern = "?"
				break
			case 'processed.hungarian.data':
				missingValuePattern = "?"
				break
			case 'processed.switzerland.data':
				missingValuePattern = "?"
				break
			case 'processed.va.data':
				missingValuePattern = "?"
				break
			case 'promoters.data':
			//no missing values
				break
			case 'Reaction Network (Undirected).data':
				missingValuePattern = "?"
				break
			case 'Relation Network (Directed).data':
			//no missing values
				break
			case 'reprocessed.hungarian.data':
			//does not have data in correct format TODO change delimiter to comma
				return
			case 'results.data':
			//no missing values
				break
			case 'secom.data':
			//problem with saving NaN in SQL
				return
			case 'secom_labels.data':
			//does not have data in correct format TODO change delimiter to comma
				return
			case 'segmentation.data':
			//TODO remove first line
			//no missing values
				break
			case 'semeion.data':
			//no missing values
				break
			case 'sensor_readings_2.data':
			// no missing values
				break
			case 'sensor_readings_24.data':
			//no missing values
				break
			case 'sensor_readings_4.data':
			//no missing values
				break
			case 'servo.data':
			//no missing values
				break
			case 'shuttle-landing-control.data':
				missingValuePattern="*"
				break
			case 'sick-euthyroid.data':
				missingValuePattern = "?"
				break
			case 'sick.data':
				missingValuePattern = "?"
				break
			case 'slump_test.data':
			//no missing values
				break
			case 'soybean-large.data':
				missingValuePattern = "?"
				break
			case 'soybean-small.data':
			//no missing values
				break
			case 'spambase.data':
			//no missing values??
				break
			case 'splice 2.data':
				break
			case 'splice.data':
			//no missing values
				break
			case 'sponge.data':
				missingValuePattern = "?"
				break
			case 'switzerland.data':
			//does not contain data in correct format
				return
			case 'synthetic.data':
			//does not have correct format TODO change delimiter to comma
				break
			case 'synthetic_control.data':
			//does not have data in correct format TODO change delimiter to comma
				return
			case 'tae.data':
			//no missing values
				break
			case 'test1.data':
			//does not contain data in correct format
				return
			case 'test2.data':
			//does not contain data in correct format
				return
			case 'test3.data':
			//does not contain data in correct format
				return
			case 'test4.data':
			//does not contain data in correct format
				return
			case 'test5.data':
			//does not contain data in correct format
				return
			case 'test6.data':
			//does not contain data in correct format
				return
			case 'thyroid0387.data':
				missingValuePattern ="?"
				break
			case 'tic-tac-toe.data':
			//no missing values
				break
			case 'train1.data':
			//does not contain data in correct format
				return
			case 'train2.data':
			//does not contain data in correct format
				return
			case 'train3.data':
			//does not contain data in correct format
				return
			case 'train4.data':
			//does not contain data in correct format
				return
			case 'train5.data':
			//does not contain data in correct format
				return
			case 'train6.data':
			//does not contain data in correct format
				return
			case 'trains-original.data':
			//does not have appropriate data
				return
			case 'trains-transformed.data':
			//does not have appropriate data TODO change delimiter
				return
			case 'transfusion.data':
			//no missing values
				break
			case 'turn.data':
			//no missing values
				break
			case 'university.data':
			//does not have appropriate data
				return
			case 'USCensus1990.data.txt':
			//no missing values - needs 12GB of RAM to parse
				break
			case 'USCensus1990raw.data.txt':
			//does not have appropriate data
				return
			case 'vowel-context.data':
			//does not contain data in correct format
				return
			case 'vowel.data':
			//does not contain data in correct format
				return
			case 'water-treatment.data':
				missingValuePattern="?"
				break
			case 'waveform-+noise.data':
			//no missing values
				break
			case 'waveform.data':
			//no missing values
				break
			case 'wdbc.data':
			//no missing values
				break
			case 'wine.data':
			//no missing values
				break
			case 'wpbc.data':
				missingValuePattern="?"
				break
			case 'yacht_hydrodynamics.data':
			//				does not have data in correct format TODO change delimiter to comma
				return
			case 'yeast.data':
			//			does not have data in correct format TODO change delimiter to comma
				return
			case 'yellow-small+adult-stretch.data':
			//no missing values
				break
			case 'yellow-small.data':
			//no missing values
				break
			case 'zoo.data':
			//no missing values
				break
			default:
				println "dataset NOT found: ${file.name}"
				return
		}
		//end of the swith

				datasetService.saveDataset(datasetName, datasetName + " dataset from UCI", file, missingValuePattern)
	}

	def destroy = {
	}
}