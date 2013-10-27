package winston



import grails.test.mixin.*

import org.junit.*
import org.springframework.mock.web.MockMultipartFile
import org.springframework.mock.web.MockMultipartHttpServletRequest

import sk.upjs.winston.groovy.*

@TestFor(DatasetController)
@Mock(Dataset)
class DatasetControllerTests {
	
	@Before
	void setUp() {
		//setup code is here
	}

    def populateValidParams(params) {
        assert params != null
        //Populate valid properties like...
		params["title"] = 'DatasetController test'
		params["dataFile"] = 'testDataset.data'
		params["description"] = 'DatasetController test.'
		params["missingValuePattern"] = ''
    }

    void testIndex() {
        controller.index()
        assert "/dataset/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.datasetInstanceList.size() == 0
        assert model.datasetInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.datasetInstance != null
    }

    void testSave() {
		def imgContentBytes = '123, false, aha\n' as byte[]
		controller.metaClass.request = new MockMultipartHttpServletRequest()
		controller.request.addFile(
			new MockMultipartFile('dataFile','testDataset.data', 'image/jpeg', imgContentBytes)
		)
		
		
		
		
        controller.save()

        assert model.datasetInstance != null
        assert view == '/dataset/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/dataset/show/1'
        assert controller.flash.message != null
        assert Dataset.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/dataset/list'

        populateValidParams(params)
        def dataset = new Dataset(params)

        assert dataset.save() != null

        params.id = dataset.id

        def model = controller.show()

        assert model.datasetInstance == dataset
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/dataset/list'

        populateValidParams(params)
        def dataset = new Dataset(params)

        assert dataset.save() != null

        params.id = dataset.id

        def model = controller.edit()

        assert model.datasetInstance == dataset
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/dataset/list'

        response.reset()

        populateValidParams(params)
        def dataset = new Dataset(params)

        assert dataset.save() != null

        // test invalid parameters in update
        params.id = dataset.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/dataset/edit"
        assert model.datasetInstance != null

        dataset.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/dataset/show/$dataset.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        dataset.clearErrors()

        populateValidParams(params)
        params.id = dataset.id
        params.version = -1
        controller.update()

        assert view == "/dataset/edit"
        assert model.datasetInstance != null
        assert model.datasetInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/dataset/list'

        response.reset()

        populateValidParams(params)
        def dataset = new Dataset(params)

        assert dataset.save() != null
        assert Dataset.count() == 1

        params.id = dataset.id

        controller.delete()

        assert Dataset.count() == 0
        assert Dataset.get(dataset.id) == null
        assert response.redirectedUrl == '/dataset/list'
    }
}
