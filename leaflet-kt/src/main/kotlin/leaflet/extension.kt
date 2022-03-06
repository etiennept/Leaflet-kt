package leaflet

var Control.position
    get() = getPosition()
    set(value) {
        setPosition(value)
    }

val Control.container
    get() = getContainer()


var Marker.icon
    get() = getIcon()
    set(value) {
        setIcon(value)
    }

var Marker.latlng
    get() = getLatLng()
    set(value) {
        setLatLng(value)
    }


val Layer.getPane
    get() = getPane()

var Popup.latlng
    get() = getLatLng()
    set(value) {
        setLatLng(value)
    }

var Map.zoom
    get() = getZoom()
    set(value) {
        setZoom(value)
    }

var Map.maxZoom
    get() = getMaxZoom()
    set(value) {
        setMaxZoom(value)
    }
var Map.minZoom
    get() = getMinZoom()
    set(value) {
        setMinZoom(value)
    }


var Map.center
    get() = getCenter()
    set(value) {
        panTo(value)
    }

