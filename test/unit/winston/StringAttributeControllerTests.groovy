package winston



import org.junit.*
import grails.test.mixin.*

@TestFor(StringAttributeController)
@Mock(StringAttribute)
class StringAttributeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/stringAttribute/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stringAttributeInstanceList.size() == 0
        assert model.stringAttributeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.stringAttributeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.stringAttributeInstance != null
        assert view == '/stringAttribute/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/stringAttribute/show/1'
        assert controller.flash.message != null
        assert StringAttribute.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/stringAttribute/list'

        populateValidParams(params)
        def stringAttribute = new StringAttribute(params)

        assert stringAttribute.save() != null

        params.id = stringAttribute.id

        def model = controller.show()

        assert model.stringAttributeInstance == stringAttribute
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/stringAttribute/list'

        populateValidParams(params)
        def stringAttribute = new StringAttribute(params)

        assert stringAttribute.save() != null

        params.id = stringAttribute.id

        def model = controller.edit()

        assert model.stringAttributeInstance == stringAttribute
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/stringAttribute/list'

        response.reset()

        populateValidParams(params)
        def stringAttribute = new StringAttribute(params)

        assert stringAttribute.save() != null

        // test invalid parameters in update
        params.id = stringAttribute.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/stringAttribute/edit"
        assert model.stringAttributeInstance != null

        stringAttribute.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/stringAttribute/show/$stringAttribute.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        stringAttribute.clearErrors()

        populateValidParams(params)
        params.id = stringAttribute.id
        params.version = -1
        controller.update()

        assert view == "/stringAttribute/edit"
        assert model.stringAttributeInstance != null
        assert model.stringAttributeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/stringAttribute/list'

        response.reset()

        populateValidParams(params)
        def stringAttribute = new StringAttribute(params)

        assert stringAttribute.save() != null
        assert StringAttribute.count() == 1

        params.id = stringAttribute.id

        controller.delete()

        assert StringAttribute.count() == 0
        assert StringAttribute.get(stringAttribute.id) == null
        assert response.redirectedUrl == '/stringAttribute/list'
    }
}
