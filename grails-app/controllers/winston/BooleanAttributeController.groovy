package winston

import org.springframework.dao.DataIntegrityViolationException

class BooleanAttributeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [booleanAttributeInstanceList: BooleanAttribute.list(params), booleanAttributeInstanceTotal: BooleanAttribute.count()]
    }

    def create() {
        [booleanAttributeInstance: new BooleanAttribute(params)]
    }

    def save() {
        def booleanAttributeInstance = new BooleanAttribute(params)
        if (!booleanAttributeInstance.save(flush: true)) {
            render(view: "create", model: [booleanAttributeInstance: booleanAttributeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'booleanAttribute.label', default: 'BooleanAttribute'), booleanAttributeInstance.id])
        redirect(action: "show", id: booleanAttributeInstance.id)
    }

    def show(Long id) {
        def booleanAttributeInstance = BooleanAttribute.get(id)
        if (!booleanAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'booleanAttribute.label', default: 'BooleanAttribute'), id])
            redirect(action: "list")
            return
        }

        [booleanAttributeInstance: booleanAttributeInstance]
    }

    def edit(Long id) {
        def booleanAttributeInstance = BooleanAttribute.get(id)
        if (!booleanAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'booleanAttribute.label', default: 'BooleanAttribute'), id])
            redirect(action: "list")
            return
        }

        [booleanAttributeInstance: booleanAttributeInstance]
    }

    def update(Long id, Long version) {
        def booleanAttributeInstance = BooleanAttribute.get(id)
        if (!booleanAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'booleanAttribute.label', default: 'BooleanAttribute'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (booleanAttributeInstance.version > version) {
                booleanAttributeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'booleanAttribute.label', default: 'BooleanAttribute')] as Object[],
                          "Another user has updated this BooleanAttribute while you were editing")
                render(view: "edit", model: [booleanAttributeInstance: booleanAttributeInstance])
                return
            }
        }

        booleanAttributeInstance.properties = params

        if (!booleanAttributeInstance.save(flush: true)) {
            render(view: "edit", model: [booleanAttributeInstance: booleanAttributeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'booleanAttribute.label', default: 'BooleanAttribute'), booleanAttributeInstance.id])
        redirect(action: "show", id: booleanAttributeInstance.id)
    }

    def delete(Long id) {
        def booleanAttributeInstance = BooleanAttribute.get(id)
        if (!booleanAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'booleanAttribute.label', default: 'BooleanAttribute'), id])
            redirect(action: "list")
            return
        }

        try {
            booleanAttributeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'booleanAttribute.label', default: 'BooleanAttribute'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'booleanAttribute.label', default: 'BooleanAttribute'), id])
            redirect(action: "show", id: id)
        }
    }
}
