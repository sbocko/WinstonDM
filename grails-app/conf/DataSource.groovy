dataSource {
    pooled = true
//    driverClassName = "org.h2.Driver"
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
	driverClassName = "com.mysql.jdbc.Driver"
	//
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
//        dataSource {
//            dbCreate = "create" // one of 'create', 'create-drop', 'update', 'validate', ''
//            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
//        }
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:mysql://stefanbocko.sk/nh2096401db?useUnicode=yes&characterEncoding=UTF-8"
			username = "nh2096401"
			password = "taraystol"
//			url = "localhost"
//			username = "bocko"
//			password = "n3RaDwEUyDSwqREC"
		}
		hibernate {
			show_sql = true
		}
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
//        dataSource {
//            dbCreate = "update"
//            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
//            pooled = true
//            properties {
//               maxActive = -1
//               minEvictableIdleTimeMillis=1800000
//               timeBetweenEvictionRunsMillis=1800000
//               numTestsPerEvictionRun=3
//               testOnBorrow=true
//               testWhileIdle=true
//               testOnReturn=true
//               validationQuery="SELECT 1"
//            }
//        }
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			url = "jdbc:mysql://stefanbocko.sk/nh2096401db?useUnicode=yes&characterEncoding=UTF-8"
			username = "nh2096401"
			password = "taraystol"
		}
    }
}
