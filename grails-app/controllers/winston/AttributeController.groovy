package winston

import org.springframework.dao.DataIntegrityViolationException

class AttributeController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[attributeInstanceList: Attribute.list(params), attributeInstanceTotal: Attribute.count()]
	}

	def create() {
		[attributeInstance: new Attribute(params)]
	}

	def save() {
		def attributeInstance = new Attribute(params)
		if (!attributeInstance.save(flush: true)) {
			render(view: "create", model: [attributeInstance: attributeInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'attribute.label', default: 'Attribute'),
			attributeInstance.id
		])
		redirect(action: "show", id: attributeInstance.id)
	}

	/**
	 * This method will show attributes metadata when it is instance of Attribute.class. 
	 * Otherwise it pass the attribute instance to appropriate controller and calls the show method.
	 * @param	id	the attribute ID in database.
	 */
	def show(Long id) {
		def attributeInstance = Attribute.get(id)
		if (!attributeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'attribute.label', default: 'Attribute'),
				id
			])
			redirect(action: "list")
			return
		}

		if(attributeInstance.instanceOf(BooleanAttribute)){
			redirect(controller: BooleanAttribute.class.getSimpleName(), action: "show", id: attributeInstance.id)
			println "bool redirect"
		}else if(attributeInstance.instanceOf(StringAttribute)){
			redirect(controller: StringAttribute.class.getSimpleName(), action: "show", id: attributeInstance.id)
			println "string redirect"
		}else if(attributeInstance.instanceOf(NumericAttribute)){
			redirect(controller: NumericAttribute.class.getSimpleName(), action: "show", id: attributeInstance.id)
			println "numeric redirect"
		}


		[attributeInstance: attributeInstance]
	}

	def edit(Long id) {
		def attributeInstance = Attribute.get(id)
		if (!attributeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'attribute.label', default: 'Attribute'),
				id
			])
			redirect(action: "list")
			return
		}

		[attributeInstance: attributeInstance]
	}

	def update(Long id, Long version) {
		def attributeInstance = Attribute.get(id)
		if (!attributeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'attribute.label', default: 'Attribute'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (attributeInstance.version > version) {
				attributeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'attribute.label', default: 'Attribute')] as Object[],
						"Another user has updated this Attribute while you were editing")
				render(view: "edit", model: [attributeInstance: attributeInstance])
				return
			}
		}

		attributeInstance.properties = params

		if (!attributeInstance.save(flush: true)) {
			render(view: "edit", model: [attributeInstance: attributeInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'attribute.label', default: 'Attribute'),
			attributeInstance.id
		])
		redirect(action: "show", id: attributeInstance.id)
	}

	def delete(Long id) {
		def attributeInstance = Attribute.get(id)
		if (!attributeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'attribute.label', default: 'Attribute'),
				id
			])
			redirect(action: "list")
			return
		}

		try {
			attributeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'attribute.label', default: 'Attribute'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'attribute.label', default: 'Attribute'),
				id
			])
			redirect(action: "show", id: id)
		}
	}
}
