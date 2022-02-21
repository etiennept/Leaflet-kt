import org.w3c.dom.HTMLElement
import org.w3c.dom.events.UIEvent


@JsModule("leaflet")
@JsNonModule
@JsName("L")
external object Leaflet {
    abstract class Class() {

    }

    abstract class Evented : Class {
        internal fun <E : Event> on(type: String, fn: (E) -> Unit)
        internal fun <E : Event> off(type: String, fn: (E) -> Unit)
        internal fun off()
        internal fun once()
        internal fun fire()
        internal fun listens(type: String): Boolean
    }

    abstract class Handler : Class {
        fun enable(): Handler
        fun disable(): Handler
        fun enabled(): Boolean
    }

    open class Map : Evented {
        constructor(htmlElement: HTMLElement)
        constructor(id: String)

        open var zoomControl: Control.Companion.Zoom
        open var boxZoom: Handler
        open var doubleClickZoom: Handler
        open var dragging: Handler
        open var keyboard: Handler
        open var scrollWheelZoom: Handler
        open var tap: Handler
        open var touchZoom: Handler

        fun getCenter(): LatLng
        fun getZoom(): Number
        fun getMinZoom(): Number
        fun getMaxZoom(): Number
        fun distance(latlng1: LatLng, latlng2: LatLng): Double

        fun setView(latLng: LatLng, zoom: Number): Map
        fun setMinZoom(zoom: Number): Map
        fun setMaxZoom(zoom: Number): Map
        fun setZoom(zoom: Number): Map
        fun zoomIn(): Map
        fun zoomIn(delta: Int): Map



        fun addControl(control: Control): Map
        fun removeControl(control: Control): Map
        fun addLayer(Layer: Layer): Map
        fun removeLayer(Layer: Layer): Map
        fun hasLayer(Layer: Layer): Boolean
        fun eachLayer(run: (Layer) -> Unit)
        fun openPopup(popup: Popup): Map
        fun openPopup(string: String): Map
        fun openPopup(htmlElement: HTMLElement): Map
        fun closePopup(): Map
        fun closePopup(popup: Popup?): Map

        fun openTooltip(popup: Popup): Map
        fun openTooltip(string: String): Map
        fun openTooltip(htmlElement: HTMLElement): Map
        fun closeTooltip(): Map
        fun closeTooltip(popup: Popup?): Map
        fun remove(): Map
        fun getContainer(): HTMLElement
        //fun addHandler( handler: Handler) : Map

        fun containerPointToLayerPoint(point: Point): Point
        fun layerPointToContainerPoint(point: Point): Point
        fun containerPointToLatLng(point: Point): LatLng
        fun latLngToContainerPoint(latLng: LatLng): Point

        companion object {

        }
    }


    abstract class Control : Class {
        fun getPosition(): String
        fun setPosition(position: String): Control
        fun getContainer(): HTMLElement
        fun addTo(map: Map): Control
        fun remove(): Control

        companion object {
            open class Zoom : Control
            open class Attribution : Control {
                fun setPrefix(prefix: String): Attribution
                fun addAttribution(attribution: String): Attribution
                fun removeAttribution(attribution: String): Attribution
            }

            open class Layers : Control {
                fun addBaseLayer(layer: Layer, name: String?): Layers
                fun addOverlay(layer: Layer, name: String?): Layers
                fun removeLayer(layer: Layer): Layers
                fun expand(): Layers
                fun collapse(): Layers
            }

            open class Scale : Control
        }
    }


    abstract class Layer : Evented {
        fun addTo(map: Map): Layer
        fun addTo(LayerGroup: LayerGroup)
        fun remove(): Layer
        fun removeFrom(map: Map)
        fun removeFrom(LayerGroup: LayerGroup)
        fun getPane(): HTMLElement
        fun getPane(name: String?): HTMLElement
        fun getAttribution(): String

        fun bindPopup(popup: Popup): Layer
        fun bindPopup(string: String): Layer
        fun bindPopup(htmlElement: HTMLElement): Layer
        fun unbindPopup(): Layer
        fun openPopup(): Layer
        fun openPopup(latLng: LatLng?): Layer
        fun closePopup(): Layer
        fun togglePopup(): Layer
        fun isPopupOpen(): Layer
        fun setPopupContent(string: String): Layer
        fun setPopupContent(htmlElement: HTMLElement): Layer
        fun setPopupContent(popup: Popup): Layer
        fun getPopup(): Popup?

        fun bindTooltip(tooltip: Tooltip): Layer
        fun bindTooltip(string: String): Layer
        fun bindTooltip(htmlElement: HTMLElement): Layer
        fun unbindTooltip(): Layer
        fun openTooltip(): Layer
        fun openTooltip(latLng: LatLng?): Layer
        fun closeTooltip(): Layer
        fun toggleTooltip(): Layer
        fun isTooltipOpen(): Layer
        fun setTooltipContent(string: String): Layer
        fun setTooltipContent(htmlElement: HTMLElement): Layer
        fun setTooltipContent(tooltip: Tooltip): Layer
        fun getTooltip(): Tooltip?
    }

    open class Marker(latLng: LatLng) : Layer {
        fun getLatLng(): LatLng
        fun setLatLng(latLng: LatLng): Marker
        fun setZIndexOffset(offset: Number): Marker
        fun setOpacity(opacity: Number): Marker
    }

    abstract class GridLayer : Layer {
        fun bringToFront(): GridLayer
        fun bringToBack(): GridLayer
        fun getContainer(): HTMLElement
        fun setOpacity(opacity: Number): GridLayer
        fun setZIndex(index: Number): GridLayer
        fun isLoading(): Boolean
        fun redraw(): GridLayer
        //fun getTileSize()
    }

    open class TileLayer(url: String) : GridLayer {
        fun setUrl(url: String): TileLayer
        fun setUrl(url: String, noRedraw: Boolean): TileLayer

        //fun getTileUrl() : String
    }

    open class LayerGroup {
        constructor()
        constructor(array: Array<Layer>)

        fun addLayer(Layer: Layer): LayerGroup
        fun removeLayer(Layer: Layer): LayerGroup
        fun removeLayer(id: Number): LayerGroup
        fun hasLayer(id: Number): Boolean
        fun hasLayer(Layer: Layer): Boolean
        fun clearLayers(): LayerGroup
        fun toGeoJSON(id: Number): Any
        fun eachLayer(on: (Layer) -> Unit)
        fun getLayer(id: Number)
        fun getLayers(): Array<Layer>
        fun setZIndex( zIndex : Number) : LayerGroup
        fun getLayerId(Layer: Layer): Number
    }

    open class FeatureGroup(array: Array<Layer>) : LayerGroup{
        fun bringToFront( ) :FeatureGroup
        fun bringToBack() : FeatureGroup
        fun getBounds() : LatLngBounds
    }
    open class GeoJSON : FeatureGroup{
        constructor()
        constructor(geoJson : Any )

        fun addData(data : dynamic ) :GeoJSON
        fun resetStyle( ) :GeoJSON
        fun resetStyle(layer :dynamic ) :GeoJSON
        fun setStyle(style :dynamic) : GeoJSON
    }

    abstract class Path{
        fun redraw() : Path
        fun bringToFront () : Path
        fun bringToBack() : Path

    }

    class Polyline : Path {


    }
    abstract class DivOverlay

    open class Popup() : DivOverlay {
        fun getLatLng(): LatLng
        fun setLatLng(latLng: LatLng): Popup
        fun getContent(): dynamic
        fun setContent(content: HTMLElement): Popup
        fun setContent(content: String): Popup
        fun getElement(): dynamic
        fun isOpen(): Boolean
        fun update()
        fun bringToFront(): Popup
        fun bringToBack(): Popup
        fun openOn(map: Map): Popup
    }

    open class Tooltip() : DivOverlay {}

    class LatLng {
        constructor(lat: Number, lng: Number)
        constructor(lat: Number, lng: Number, alt: Number)
        constructor(array: Array<Number>)

        var lat: Number
        var lng: Number
        fun wrap(): LatLng
        fun distanceTo(otherLatLng: LatLng): Number
        fun equals(otherLatLng: LatLng, maxMargin: Number): Boolean
        fun toBounds(sizeInMeters: Number): LatLngBounds
        override fun equals(other: Any?): Boolean
        override fun toString(): String
    }

    class LatLngBounds {
        constructor(corner1: LatLng, corner2: LatLng)
        constructor(array: Array<LatLng>)

        fun getCenter(): LatLng
        fun getSouthWest(): LatLng
        fun getNorthEast(): LatLng
        fun getNorthWest(): LatLng
        fun getSouthEast(): LatLng
        fun getWest(): Number
        fun getEast(): Number
        fun getNorth(): Number
        fun getSouth(): Number
        fun contains(otherBound: LatLngBounds): Boolean
        fun contains(latLng: LatLng): Boolean
        fun intersects(otherBound: LatLngBounds): Boolean
        fun overlaps(otherBound: LatLngBounds): Boolean
        fun toBBoxString(): String
        fun equals(otherBound: LatLngBounds, maxMargin: Number): Boolean
        override fun equals(other: Any?): Boolean
    }

    class Point {
        constructor(x: Number, y: Number)
        constructor(x: Number, y: Number, round: Boolean?)
        constructor(array: Array<Number>)

        var x: Number
        var y: Number
        fun clone(): Point
        fun add(otherPoint: Point): Point
        fun subtract(otherPoint: Point): Point
        fun divideBy(number: Number): Point
        fun multiplyBy(number: Number): Point
        fun scaleBy(scale: Point): Point
        fun unscaleBy(scale: Point): Point
        fun round(): Point
        fun floor(): Point
        fun ceil(): Point
        fun trunc(): Point
        fun distanceTo(otherPoint: Point): Number
        override fun equals(other: Any?): Boolean
        fun contains(otherPoint: Point): Point
        override fun toString(): String
    }

    open class Event {
        open val type: String
        open val target: Any
        open val sourceTarget: Any
        open val propagatedFrom: Any
    }

    open class KeyboardEvent : Event {
        open val originalEvent: org.w3c.dom.events.KeyboardEvent
    }

    open class MouseEvent : Event {
        open val latlng: LatLng
        open val layerPoint: Point
        open val containerPoint: Point
        open val originalEvent: UIEvent
    }

    open class LocationEvent : Event {
        open val latlng: LatLng
        open val bounds: LatLngBounds
        open val accuracy: Number
        open val altitude: Number
        open val altitudeAccuracy: Number
        open val heading: Number
        open val speed: Number
        open val timestamp: Number
    }

    open class ErrorEvent : Event {
        open val message: String
        open val code: Number?
    }

    open class LayerEvent : Event {
        open val layer: Layer
    }

    open class LayersControlEvent : Event {
        open val layer: Layer
        open val name: String
    }

    open class TileEvent : Event {
        open val tile: HTMLElement
        open val coords: Point
    }

    open class TileErrorEvent : Event {
        open val tile: HTMLElement
        open val coords: Point
        open val error: dynamic
    }

    open class ResizeEvent : Event {
        open val odlSize: Point
        open val newSize: Point
    }

    open class GeoJSONEvent : Event {
        open val layer: Layer
        open val properties: Any
        open val geometryType: String
        open val id: String
    }

    open class PopupEvent : Event {
        open val popup: Popup
    }

    open class TooltipEvent : Event {
        open val tooltip: Tooltip
    }

    open class DragEndEvent : Event {
        val distance: Number
    }

    open class ZoomAnimEvent : Event {
        val center: LatLng
        val zoom: Number
        val noUpdate: Boolean
    }
}

