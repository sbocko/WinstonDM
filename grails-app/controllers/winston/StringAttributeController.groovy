package winston

import org.springframework.dao.DataIntegrityViolationException

class StringAttributeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [stringAttributeInstanceList: StringAttribute.list(params), stringAttributeInstanceTotal: StringAttribute.count()]
    }

    def create() {
        [stringAttributeInstance: new StringAttribute(params)]
    }

    def save() {
        def stringAttributeInstance = new StringAttribute(params)
        if (!stringAttributeInstance.save(flush: true)) {
            render(view: "create", model: [stringAttributeInstance: stringAttributeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'stringAttribute.label', default: 'StringAttribute'), stringAttributeInstance.id])
        redirect(action: "show", id: stringAttributeInstance.id)
    }

    def show(Long id) {
        def stringAttributeInstance = StringAttribute.get(id)
        if (!stringAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stringAttribute.label', default: 'StringAttribute'), id])
            redirect(action: "list")
            return
        }

        [stringAttributeInstance: stringAttributeInstance]
    }

    def edit(Long id) {
        def stringAttributeInstance = StringAttribute.get(id)
        if (!stringAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stringAttribute.label', default: 'StringAttribute'), id])
            redirect(action: "list")
            return
        }

        [stringAttributeInstance: stringAttributeInstance]
    }

    def update(Long id, Long version) {
        def stringAttributeInstance = StringAttribute.get(id)
        if (!stringAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stringAttribute.label', default: 'StringAttribute'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (stringAttributeInstance.version > version) {
                stringAttributeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'stringAttribute.label', default: 'StringAttribute')] as Object[],
                          "Another user has updated this StringAttribute while you were editing")
                render(view: "edit", model: [stringAttributeInstance: stringAttributeInstance])
                return
            }
        }

        stringAttributeInstance.properties = params

        if (!stringAttributeInstance.save(flush: true)) {
            render(view: "edit", model: [stringAttributeInstance: stringAttributeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'stringAttribute.label', default: 'StringAttribute'), stringAttributeInstance.id])
        redirect(action: "show", id: stringAttributeInstance.id)
    }

    def delete(Long id) {
        def stringAttributeInstance = StringAttribute.get(id)
        if (!stringAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stringAttribute.label', default: 'StringAttribute'), id])
            redirect(action: "list")
            return
        }

        try {
            stringAttributeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'stringAttribute.label', default: 'StringAttribute'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stringAttribute.label', default: 'StringAttribute'), id])
            redirect(action: "show", id: id)
        }
    }
}
