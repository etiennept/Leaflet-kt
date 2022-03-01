import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.Option
import org.w3c.dom.events.UIEvent
import org.w3c.dom.svg.SVGElement


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
        open fun enable(): Handler
        open fun disable(): Handler
        open fun enabled(): Boolean
    }

    open class Map : Evented {


        constructor(htmlElement: HTMLElement, option: Option? = definedExternally)
        constructor(id: String, option: Option? = definedExternally)

        interface Option {
            var center: LatLng
            var zoom: Number
            var layers: Array<*>
            var crs: CRS
            var minZoom: Number
            var maxZoom: Number
            var maxBounds: LatLngBounds
            var renderer: Renderer
            var zoomAnimation: Boolean
            var zoomAnimationThreshold: Number
            var fadeAnimation: Number
            var markerZoomAnimation: Number
            var transform3DLimit: Number
            var zoomSnap: Number
        }

        val control: Option

        open var zoomControl: Control.Companion.Zoom
        open var boxZoom: Handler
        open var doubleClickZoom: Handler
        open var dragging: Handler
        open var keyboard: Handler
        open var scrollWheelZoom: Handler
        open var tap: Handler
        open var touchZoom: Handler

        open fun getCenter(): LatLng
        open fun getZoom(): Number
        open fun getMinZoom(): Number
        open fun getMaxZoom(): Number

        open fun getBoundsZoom(bounds: LatLngBounds, inside: Boolean, padding: Point? = definedExternally): Number
        open fun getSize(): Point
        open fun getPixelBounds(): Bounds
        open fun getPixelOrigin(): Point
        open fun getPixelWorldBounds(): Bounds
        open fun getPixelWorldBounds(zoom: Number?): Bounds

        open fun distance(latlng1: LatLng, latlng2: LatLng): Double
        open fun setView(latLng: LatLng, zoom: Number): Map
        open fun setMinZoom(zoom: Number): Map
        open fun setMaxZoom(zoom: Number): Map
        open fun setZoom(zoom: Number): Map
        open fun zoomIn(): Map
        open fun zoomIn(delta: Int): Map

        open fun addControl(control: Control): Map
        open fun removeControl(control: Control): Map
        open fun addLayer(Layer: Layer): Map
        open fun removeLayer(Layer: Layer): Map
        open fun hasLayer(Layer: Layer): Boolean
        open fun eachLayer(run: (Layer) -> Unit)
        open fun openPopup(popup: Popup): Map
        open fun openPopup(string: String): Map
        open fun openPopup(htmlElement: HTMLElement): Map
        open fun closePopup(): Map
        open fun closePopup(popup: Popup?): Map

        open fun openTooltip(popup: Popup): Map
        open fun openTooltip(string: String): Map
        open fun openTooltip(htmlElement: HTMLElement): Map
        open fun closeTooltip(): Map
        open fun closeTooltip(popup: Popup?): Map
        open fun remove(): Map
        open fun getContainer(): HTMLElement
        //fun addHandler( handler: Handler) : Map

        open fun getZoomScale(toZoom: Number, fromZoom: Number): Number
        open fun getScaleZoom(scale: Number, fromZoom: Number): Number
        open fun project(latLng: LatLng, zoom: Number): Point
        open fun unProject(point: Point, zoom: Number): LatLng
        open fun layerPointToLatLng(point: Point): LatLng
        open fun latLngToLayerPoint(latLng: LatLng): Point
        open fun wrapLatLng(latLng: LatLng): LatLng
        open fun wrapLatLngBounds(latLngBounds: LatLngBounds): LatLngBounds
        open fun containerPointToLayerPoint(point: Point): Point
        open fun layerPointToContainerPoint(point: Point): Point
        open fun containerPointToLatLng(point: Point): LatLng
        open fun latLngToContainerPoint(latLng: LatLng): Point
        open fun mouseEventToContainerPoint(mouseEvent: MouseEvent): Point
        open fun mouseEventToLayerPoint(mouseEvent: MouseEvent): Point
        open fun mouseEventToLatLng(mouseEvent: MouseEvent): Point
        open fun locate(): Map
        open fun stopLocate(): Map

    }

    abstract class Control : Class {
        interface Option {
            val position: String
        }

        open val option: Option

        open fun getPosition(): String
        open fun setPosition(position: String): Control
        open fun getContainer(): HTMLElement
        open fun addTo(map: Map): Control
        open fun remove(): Control

        companion object {
            open class Zoom(option: Option = definedExternally) : Control {
                interface Option : Control.Option {
                    var zoomInText: String
                    var zoomInTitle: String
                    var zoomOutText: String
                    var zoomOutTitle: String
                }

                override val option: Option
            }

            open class Attribution(option: Option = definedExternally) : Control {
                interface Option : Control.Option {
                    val prefix: String
                }

                override val option: Option
                open fun setPrefix(prefix: String): Attribution
                open fun addAttribution(attribution: String): Attribution
                open fun removeAttribution(attribution: String): Attribution
            }

            open class Layers(
                baseLayer: Any? = definedExternally,
                overlays: Any? = definedExternally,
                option: Option? = definedExternally
            ) : Control {

                interface Option : Control.Option {
                    var collapsed: Boolean
                    var autoZIndex: Boolean
                    var hideSingleBase: Boolean
                    var sortLayers: Boolean
                    var sortFunction: dynamic
                }

                override val option: Option
                open fun addBaseLayer(layer: Layer, name: String?): Layers
                open fun addOverlay(layer: Layer, name: String?): Layers
                open fun removeLayer(layer: Layer): Layers
                open fun expand(): Layers
                open fun collapse(): Layers
            }

            open class Scale(option: Option = definedExternally) : Control {
                interface Option : Control.Option {
                    var maxWidth: Number
                    var metric: Boolean
                    var imperial: Boolean
                    var updateWhenIdle: Boolean
                }

                override val option: Option
            }
        }
    }


    abstract class Layer : Evented {

        interface Option {
            var pane: String
            var attribution: String
        }

        open val option: Option
        open fun addTo(map: Map): Layer
        open fun addTo(LayerGroup: LayerGroup)
        open fun remove(): Layer
        open fun removeFrom(map: Map)
        open fun removeFrom(LayerGroup: LayerGroup)
        open fun getPane(): HTMLElement
        open fun getPane(name: String?): HTMLElement
        open fun getAttribution(): String

        open fun bindPopup(): Layer
        open fun bindPopup(popup: Popup): Layer
        open fun bindPopup(string: String): Layer
        open fun bindPopup(htmlElement: HTMLElement): Layer
        open fun unbindPopup(): Layer
        open fun openPopup(): Layer
        open fun openPopup(latLng: LatLng?): Layer
        open fun closePopup(): Layer
        open fun togglePopup(): Layer
        open fun isPopupOpen(): Layer
        open fun setPopupContent(string: String): Layer
        open fun setPopupContent(htmlElement: HTMLElement): Layer
        open fun setPopupContent(popup: Popup): Layer
        open fun getPopup(): Popup?

        open fun bindTooltip(): Layer
        open fun bindTooltip(tooltip: Tooltip): Layer
        open fun bindTooltip(string: String): Layer
        open fun bindTooltip(htmlElement: HTMLElement): Layer
        open fun unbindTooltip(): Layer
        open fun openTooltip(): Layer
        open fun openTooltip(latLng: LatLng?): Layer
        open fun closeTooltip(): Layer
        open fun toggleTooltip(): Layer
        open fun isTooltipOpen(): Layer
        open fun setTooltipContent(string: String): Layer
        open fun setTooltipContent(htmlElement: HTMLElement): Layer
        open fun setTooltipContent(tooltip: Tooltip): Layer
        open fun getTooltip(): Tooltip?
    }

    abstract class InteractiveLayer : Layer {
        interface Option : Layer.Option {
            var interactive: Boolean
            var bubblingMouseEvents: Boolean
        }

        override val option: Layer.Option
    }


    open class ImageOverlay(
        url: String = definedExternally,
        bounds: LatLngBounds = definedExternally,
        option: Option = definedExternally
    ) : InteractiveLayer {
        interface Option : InteractiveLayer.Option {
            var opacity: Number
            var alt: String

            var crossOrigin: Boolean
            var errorOverlayUrl: String
            var zIndex: Number
            var className: String
        }

        override val option: Option
        open fun setOpacity(opacity: Number): ImageOverlay
        open fun bringToFront(): ImageOverlay
        open fun bringToBack(): ImageOverlay
        open fun setUrl(url: String): ImageOverlay
        open fun setZIndex(zIndex: Number): ImageOverlay
        open fun getBounds(): LatLngBounds
        open fun setBounds(latLngBounds: LatLngBounds): ImageOverlay
        open fun getElement(): Element
    }

    open class VideoOverlay : ImageOverlay {
        constructor(string: String = definedExternally, latLngBounds: LatLngBounds = definedExternally)
        constructor(array: HTMLVideoElement = definedExternally, latLngBounds: LatLngBounds)

        interface Option : ImageOverlay.Option {
            var autoplay: Boolean
            var loop: Boolean
            var keepAspectRatio: Boolean
            var muted: Boolean
        }

        override val option: Option
        override fun getElement(): HTMLVideoElement
    }

    open class SVGOverlay(
        url: String = definedExternally,
        bounds: LatLngBounds = definedExternally,
        option: Option = definedExternally
    ) : ImageOverlay {
        override fun getElement(): SVGElement
    }

    abstract class GridLayer() : Layer {
        interface Option : Layer.Option {
            var tileSize: Point
            val opacity: Number
            var updateWhenIdle: Boolean
            var updateWhenZooming: Boolean
            var updateInterval: Number
            val zIndex: Number
            val bounds: LatLngBounds
            var minZoom: Number
            var maxZoom: Number
            var maxNativeZoom: Number
            var minNativeZoom: Number
            var noWrap: Boolean
            var className: String
            var keepBuffer: Number
        }

        override val option: Option
        open fun bringToFront(): GridLayer
        open fun bringToBack(): GridLayer
        open fun getContainer(): HTMLElement
        open fun setOpacity(opacity: Number): GridLayer
        open fun setZIndex(index: Number): GridLayer
        open fun isLoading(): Boolean
        open fun redraw(): GridLayer
        //fun getTileSize()
    }

    open class TileLayer(url: String = definedExternally) : GridLayer {
        interface Option : GridLayer.Option {
            var subdomains: Array<String>
            var errorTileUrl: String
            var zoomOffset: Number
            var tms: Boolean
            var zoomReverse: Boolean
            var detectRetina: Boolean
            var crossOrigin: Boolean
        }

        override val option: Option
        open fun setUrl(url: String): TileLayer
        open fun setUrl(url: String, noRedraw: Boolean): TileLayer

        //fun getTileUrl() : String
        companion object {
            open class WMS : TileLayer
        }
    }

    open class Marker(latLng: LatLng = definedExternally, option: Option = definedExternally) : InteractiveLayer {
        interface Option : InteractiveLayer.Option {
            val icon : Icon
            var keyboard: Boolean
            var title: String
            var alt: String
            var zIndexOffset: Number
            val opacity: Number
            var riseOnHover: Boolean
            var riseOffset: Number
            var shadowPane: String
        }

        override val option: Layer.Option
        val dragging: Handler
        open fun getIcon() : Icon
        open fun setIcon(value : Icon) : Marker
        open fun getLatLng(): LatLng
        open fun setLatLng(latLng: LatLng): Marker
        open fun setZIndexOffset(offset: Number): Marker
        open fun setOpacity(opacity: Number): Marker
    }

    open class LayerGroup(array: Array<Layer> = definedExternally, option: Option = definedExternally) : Layer {

        open fun addLayer(Layer: Layer): LayerGroup
        open fun removeLayer(Layer: Layer): LayerGroup
        open fun removeLayer(id: Number): LayerGroup
        open fun hasLayer(id: Number): Boolean
        open fun hasLayer(Layer: Layer): Boolean
        open fun clearLayers(): LayerGroup
        open fun toGeoJSON(id: Number): Any
        open fun eachLayer(on: (Layer) -> Unit)
        open fun getLayer(id: Number): Layer
        open fun getLayers(): Array<Layer>
        open fun setZIndex(zIndex: Number): LayerGroup
        open fun getLayerId(Layer: Layer): Number
    }

    open class FeatureGroup(layers: Array<Layer> = definedExternally, option: Option = definedExternally) : LayerGroup {
        open fun bringToFront(): FeatureGroup
        open fun bringToBack(): FeatureGroup
        open fun getBounds(): LatLngBounds
    }

    open class GeoJSON(geoJson: Any = definedExternally, option: Option = definedExternally) : FeatureGroup {
        open fun addData(data: dynamic): GeoJSON
        open fun resetStyle(): GeoJSON
        open fun resetStyle(layer: dynamic): GeoJSON
        open fun setStyle(style: dynamic): GeoJSON
    }

    abstract class Path : InteractiveLayer {

        interface Option : InteractiveLayer.Option {
            var stroke: Number
            var color: String
            var weight: Number
            var opacity: Number
            var lineCap: String
            var lineJoin: String
            var dashArray: String?
            var dashOffset: String?
            var fill: Boolean
            var fillColor: String
            var fillOpacity: Number
            var fillRule: String

            var renderer: Renderer
            var className: String
        }

        override val option: Option
        open fun redraw(): Path
        open fun bringToFront(): Path
        open fun bringToBack(): Path

    }

    open class Polyline(array: Array<LatLng> = definedExternally, option: Option = definedExternally) : Path {

        interface Option : Path.Option {
            var smoothFactor: String
            var noClip: String
        }

        override val option: Option
        open fun getLatLngs(): Array<LatLng>
        open fun setLatLngs(array: Array<LatLng>): Polyline
        open fun isEmpty(): Boolean
        open fun closestLayerPoint(point: Point): Point
        open fun getCenter(): LatLng
        open fun getBounds(): LatLngBounds
        open fun addLatLng(array: LatLng): Polyline
    }

    open class Polygon(array: Array<LatLng> = definedExternally, option: Option = definedExternally) : Polyline {

    }

    open class Rectangle(latLngBounds: LatLngBounds = definedExternally, option: Option = definedExternally) : Polygon {
        open fun setBounds(bounds: LatLngBounds): Rectangle
    }

    open class CircleMarker(latLng: LatLng = definedExternally) : Path {
        interface Option : Path.Option {
            val radius: Number
        }

        open fun getLatLng(): LatLng
        open fun setLatLng(latLng: LatLng): CircleMarker
        open fun getRadius(): Number
        open fun setRadius(radius: Number): CircleMarker
    }

    open class Circle(latLng: LatLng = definedExternally, radius: Number = definedExternally) : CircleMarker {
        override fun getRadius(): Number
        override fun setRadius(radius: Number): CircleMarker
        open fun getBounds(): LatLngBounds
    }

    abstract class Renderer : Layer {
        interface Option : Layer.Option {
            var padding: Number
            var tolerance: Number
        }

        override val option: Option

    }

    open class SVG(option: Option = definedExternally) : Renderer
    open class Canvas(option: Option = definedExternally) : Renderer

    abstract class DivOverlay

    open class Popup() : DivOverlay {
        open fun getLatLng(): LatLng
        open fun setLatLng(latLng: LatLng): Popup
        open fun getContent(): dynamic
        open fun setContent(content: HTMLElement): Popup
        open fun setContent(content: String): Popup
        open fun getElement(): dynamic
        open fun isOpen(): Boolean
        open fun update()
        open fun bringToFront(): Popup
        open fun bringToBack(): Popup
        open fun openOn(map: Map): Popup
    }

    open class Tooltip() : DivOverlay {}
    open class Icon(option: Option = definedExternally) {
        interface Option {
            var iconUrl: String?
            var iconRetinaUrl: String?
            var iconSize: Point?
            var iconAnchor: Point?
            var popupAnchor: Point
            var tooltipAnchor: Point
            var shadowUrl: String?
            var shadowRetinaUrl: String?
            var shadowSize: Point?
            var shadowAnchor: Point?
            var className: String
        }

        open val option: Option
        open fun createIcon(htmlElement: HTMLElement)
        open fun createShadow(htmlElement: HTMLElement)
    }

    open class DivIcon(option: Option = definedExternally) : Icon {
        interface Option : Icon.Option {
            var html: HTMLElement
            var bgPos: Boolean
        }

        override val option: Option

    }

    abstract class CRS {

        open fun latLngToPoint(latLng: LatLng, zoom: Number): Point
        open fun pointToLatLng(point: Point, zoom: Number): LatLng
        open fun project(latLng: LatLng): Point
        open fun unProject(point: Point): LatLng
        open fun scale(scale: Number): Number
        open fun zoom(zoom: Number): Number
        open fun getProjectedBounds(zoom: Number): Bounds
        open fun distance(latLng1: LatLng, latLng2: LatLng): Number
        open fun wrapLatLng(latLng: LatLng): LatLng
        open fun wrapLatLngBounds(bounds: LatLngBounds): LatLngBounds
        var code: String
        var wrapLng: Array<Number>
        var wrapLat: Array<Number>
        var infinite: Boolean

        companion object {

            open class Simple : CRS
            open class Earth : CRS
            open class EPSG3395 : Earth
            open class EPSG3857 : Earth
            open class EPSG4326 : Earth
        }

    }

    class LatLng {
        constructor(lat: Number, lng: Number, alt: Number = definedExternally)
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

    class Point(x: Number, y: Number, round: Boolean? = definedExternally) {

        constructor(array: Array<Number>)

        @JsName("x")
        var width: Number

        @JsName("y")
        var heigth: Number
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

    class Bounds {
        constructor(corner1: Point, corner2: Point)
        constructor(array: Array<Point>)

        fun getCenter(): Point
        fun getBottomLeft(): Point
        fun getTopRight(): Point
        fun getTopLeft(): Point
        fun getBottomRight(): Point
        fun getSize(): Point
        fun contains(otherBound: Bounds): Boolean
        fun contains(point: Point): Boolean
        fun intersects(otherBounds: Bounds): Boolean
        fun overlaps(otherBound: Bounds): Boolean
    }

    interface Event {
        var type: String
        var target: Any
        var sourceTarget: Any
        var propagatedFrom: Any
    }

    interface KeyboardEvent : Event {
        var originalEvent: org.w3c.dom.events.KeyboardEvent
    }

    interface MouseEvent : Event {
        var latlng: LatLng
        var layerPoint: Point
        var containerPoint: Point
        var originalEvent: UIEvent
    }

    interface LocationEvent : Event {
        var latlng: LatLng
        var bounds: LatLngBounds
        var accuracy: Number
        var altitude: Number
        var altitudeAccuracy: Number
        var heading: Number
        var speed: Number
        var timestamp: Number
    }

    interface ErrorEvent : Event {
        var message: String
        var code: Number?
    }

    interface LayerEvent : Event {
        val layer: Layer
    }

    interface LayersControlEvent : Event {
        var layer: Layer
        var name: String
    }

    interface TileEvent : Event {
        var tile: HTMLElement
        var coords: Point
    }

    interface TileErrorEvent : Event {
        var tile: HTMLElement
        var coords: Point
        var error: dynamic
    }

    interface ResizeEvent : Event {
        var odlSize: Point
        var newSize: Point
    }

    interface GeoJSONEvent : Event {
        var layer: Layer
        var properties: Any
        var geometryType: String
        var id: String
    }

    interface PopupEvent : Event {
        var popup: Popup
    }

    interface TooltipEvent : Event {
        var tooltip: Tooltip
    }

    interface DragEndEvent : Event {
        var distance: Number
    }

    interface ZoomAnimEvent : Event {
        var center: LatLng
        var zoom: Number
        var noUpdate: Boolean
    }
}

