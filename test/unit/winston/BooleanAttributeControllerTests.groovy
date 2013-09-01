package winston



import org.junit.*
import grails.test.mixin.*

@TestFor(BooleanAttributeController)
@Mock(BooleanAttribute)
class BooleanAttributeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/booleanAttribute/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.booleanAttributeInstanceList.size() == 0
        assert model.booleanAttributeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.booleanAttributeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.booleanAttributeInstance != null
        assert view == '/booleanAttribute/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/booleanAttribute/show/1'
        assert controller.flash.message != null
        assert BooleanAttribute.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/booleanAttribute/list'

        populateValidParams(params)
        def booleanAttribute = new BooleanAttribute(params)

        assert booleanAttribute.save() != null

        params.id = booleanAttribute.id

        def model = controller.show()

        assert model.booleanAttributeInstance == booleanAttribute
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/booleanAttribute/list'

        populateValidParams(params)
        def booleanAttribute = new BooleanAttribute(params)

        assert booleanAttribute.save() != null

        params.id = booleanAttribute.id

        def model = controller.edit()

        assert model.booleanAttributeInstance == booleanAttribute
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/booleanAttribute/list'

        response.reset()

        populateValidParams(params)
        def booleanAttribute = new BooleanAttribute(params)

        assert booleanAttribute.save() != null

        // test invalid parameters in update
        params.id = booleanAttribute.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/booleanAttribute/edit"
        assert model.booleanAttributeInstance != null

        booleanAttribute.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/booleanAttribute/show/$booleanAttribute.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        booleanAttribute.clearErrors()

        populateValidParams(params)
        params.id = booleanAttribute.id
        params.version = -1
        controller.update()

        assert view == "/booleanAttribute/edit"
        assert model.booleanAttributeInstance != null
        assert model.booleanAttributeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/booleanAttribute/list'

        response.reset()

        populateValidParams(params)
        def booleanAttribute = new BooleanAttribute(params)

        assert booleanAttribute.save() != null
        assert BooleanAttribute.count() == 1

        params.id = booleanAttribute.id

        controller.delete()

        assert BooleanAttribute.count() == 0
        assert BooleanAttribute.get(booleanAttribute.id) == null
        assert response.redirectedUrl == '/booleanAttribute/list'
    }
}
