package sk.upjs.winston

import org.apache.commons.lang.builder.HashCodeBuilder

class WinstonUserWinstonRole implements Serializable {

	WinstonUser winstonUser
	WinstonRole winstonRole

	boolean equals(other) {
		if (!(other instanceof WinstonUserWinstonRole)) {
			return false
		}

		other.winstonUser?.id == winstonUser?.id &&
			other.winstonRole?.id == winstonRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (winstonUser) builder.append(winstonUser.id)
		if (winstonRole) builder.append(winstonRole.id)
		builder.toHashCode()
	}

	static WinstonUserWinstonRole get(long winstonUserId, long winstonRoleId) {
		find 'from WinstonUserWinstonRole where winstonUser.id=:winstonUserId and winstonRole.id=:winstonRoleId',
			[winstonUserId: winstonUserId, winstonRoleId: winstonRoleId]
	}

	static WinstonUserWinstonRole create(WinstonUser winstonUser, WinstonRole winstonRole, boolean flush = false) {
		new WinstonUserWinstonRole(winstonUser: winstonUser, winstonRole: winstonRole).save(flush: flush, insert: true)
	}

	static boolean remove(WinstonUser winstonUser, WinstonRole winstonRole, boolean flush = false) {
		WinstonUserWinstonRole instance = WinstonUserWinstonRole.findByWinstonUserAndWinstonRole(winstonUser, winstonRole)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(WinstonUser winstonUser) {
		executeUpdate 'DELETE FROM WinstonUserWinstonRole WHERE winstonUser=:winstonUser', [winstonUser: winstonUser]
	}

	static void removeAll(WinstonRole winstonRole) {
		executeUpdate 'DELETE FROM WinstonUserWinstonRole WHERE winstonRole=:winstonRole', [winstonRole: winstonRole]
	}

	static mapping = {
		id composite: ['winstonRole', 'winstonUser']
		version false
	}
}
