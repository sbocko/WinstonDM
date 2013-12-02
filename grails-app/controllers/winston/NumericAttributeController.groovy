package winston

import org.springframework.dao.DataIntegrityViolationException

class NumericAttributeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [numericAttributeInstanceList: NumericAttribute.list(params), numericAttributeInstanceTotal: NumericAttribute.count()]
    }

    def create() {
        [numericAttributeInstance: new NumericAttribute(params)]
    }

    def save() {
        def numericAttributeInstance = new NumericAttribute(params)
        if (!numericAttributeInstance.save(flush: true)) {
            render(view: "create", model: [numericAttributeInstance: numericAttributeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'numericAttribute.label', default: 'NumericAttribute'), numericAttributeInstance.id])
        redirect(action: "show", id: numericAttributeInstance.id)
    }

    def show(Long id) {
        def numericAttributeInstance = NumericAttribute.get(id)
        if (!numericAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'numericAttribute.label', default: 'NumericAttribute'), id])
            redirect(action: "list")
            return
        }

        [numericAttributeInstance: numericAttributeInstance]
    }

    def edit(Long id) {
        def numericAttributeInstance = NumericAttribute.get(id)
        if (!numericAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'numericAttribute.label', default: 'NumericAttribute'), id])
            redirect(action: "list")
            return
        }

        [numericAttributeInstance: numericAttributeInstance]
    }

    def update(Long id, Long version) {
        def numericAttributeInstance = NumericAttribute.get(id)
        if (!numericAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'numericAttribute.label', default: 'NumericAttribute'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (numericAttributeInstance.version > version) {
                numericAttributeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'numericAttribute.label', default: 'NumericAttribute')] as Object[],
                          "Another user has updated this NumericAttribute while you were editing")
                render(view: "edit", model: [numericAttributeInstance: numericAttributeInstance])
                return
            }
        }

        numericAttributeInstance.properties = params

        if (!numericAttributeInstance.save(flush: true)) {
            render(view: "edit", model: [numericAttributeInstance: numericAttributeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'numericAttribute.label', default: 'NumericAttribute'), numericAttributeInstance.id])
        redirect(action: "show", id: numericAttributeInstance.id)
    }

    def delete(Long id) {
        def numericAttributeInstance = NumericAttribute.get(id)
        if (!numericAttributeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'numericAttribute.label', default: 'NumericAttribute'), id])
            redirect(action: "list")
            return
        }

        try {
            numericAttributeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'numericAttribute.label', default: 'NumericAttribute'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'numericAttribute.label', default: 'NumericAttribute'), id])
            redirect(action: "show", id: id)
        }
    }
}
