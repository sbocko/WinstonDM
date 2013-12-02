package winston



import org.junit.*
import grails.test.mixin.*

@TestFor(NumericAttributeController)
@Mock(NumericAttribute)
class NumericAttributeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/numericAttribute/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.numericAttributeInstanceList.size() == 0
        assert model.numericAttributeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.numericAttributeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.numericAttributeInstance != null
        assert view == '/numericAttribute/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/numericAttribute/show/1'
        assert controller.flash.message != null
        assert NumericAttribute.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/numericAttribute/list'

        populateValidParams(params)
        def numericAttribute = new NumericAttribute(params)

        assert numericAttribute.save() != null

        params.id = numericAttribute.id

        def model = controller.show()

        assert model.numericAttributeInstance == numericAttribute
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/numericAttribute/list'

        populateValidParams(params)
        def numericAttribute = new NumericAttribute(params)

        assert numericAttribute.save() != null

        params.id = numericAttribute.id

        def model = controller.edit()

        assert model.numericAttributeInstance == numericAttribute
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/numericAttribute/list'

        response.reset()

        populateValidParams(params)
        def numericAttribute = new NumericAttribute(params)

        assert numericAttribute.save() != null

        // test invalid parameters in update
        params.id = numericAttribute.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/numericAttribute/edit"
        assert model.numericAttributeInstance != null

        numericAttribute.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/numericAttribute/show/$numericAttribute.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        numericAttribute.clearErrors()

        populateValidParams(params)
        params.id = numericAttribute.id
        params.version = -1
        controller.update()

        assert view == "/numericAttribute/edit"
        assert model.numericAttributeInstance != null
        assert model.numericAttributeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/numericAttribute/list'

        response.reset()

        populateValidParams(params)
        def numericAttribute = new NumericAttribute(params)

        assert numericAttribute.save() != null
        assert NumericAttribute.count() == 1

        params.id = numericAttribute.id

        controller.delete()

        assert NumericAttribute.count() == 0
        assert NumericAttribute.get(numericAttribute.id) == null
        assert response.redirectedUrl == '/numericAttribute/list'
    }
}
