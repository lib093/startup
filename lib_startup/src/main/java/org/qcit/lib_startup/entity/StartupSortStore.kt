package org.qcit.lib_startup.entity

import org.qcit.lib_startup.interfaces.Startup

data class StartupSortStore(var result: List<Startup<*>>,
                            var startupMap :Map<Class<out Startup<*>>,Startup<*>>,
                            var startupChildrenMap : Map<Class<out Startup<*>>,List<Class<out Startup<*>>>>) {
}