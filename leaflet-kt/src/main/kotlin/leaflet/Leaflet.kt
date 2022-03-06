@file:JsModule("leaflet")
@file:JsNonModule

package leaflet

import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.events.UIEvent
import org.w3c.dom.svg.SVGElement


abstract external class Class() {

}

abstract external class Evented : Class {
    open fun <E : Event<out Evented>> on(type: String, fn: (E) -> Unit)
    open fun <E : Event<out Evented>> off(type: String, fn: (E) -> Unit)
    internal fun off()
    internal fun once()
    internal fun fire()
    internal fun listens(type: String): Boolean
}

abstract external class Handler : Class {
    open fun enable(): Handler
    open fun disable(): Handler
    open fun enabled(): Boolean
}

open external class Map : Evented {

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
    open fun zoomIn(delta: Int? = definedExternally): Map
    open fun zoomOut(delta: Int? = definedExternally): Map
    open fun setZoomAround(latlng: LatLng, zoom: Number): Map
    open fun setZoomAround(point: Point, zoom: Number): Map
    open fun panTo(latLng: LatLng): Map
    open fun panBy(latLng: Point): Map
    open fun stop()

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
    open fun mouseEventToContainerPoint(mouseEvent: MouseEvent<Map>): Point
    open fun mouseEventToLayerPoint(mouseEvent: MouseEvent<Map>): Point
    open fun mouseEventToLatLng(mouseEvent: MouseEvent<Map>): Point
    open fun locate(): Map
    open fun stopLocate(): Map

}

abstract external class Control : Class {
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

            override fun setPosition(position: String): Zoom
            override fun addTo(map: Map): Zoom
            override fun remove(): Zoom

        }

        open class Attribution(option: Option = definedExternally) : Control {
            interface Option : Control.Option {
                val prefix: String
            }

            override val option: Option
            open fun setPrefix(prefix: String): Attribution
            open fun addAttribution(attribution: String): Attribution
            open fun removeAttribution(attribution: String): Attribution

            override fun setPosition(position: String): Attribution
            override fun addTo(map: Map): Attribution
            override fun remove(): Attribution
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

            override fun setPosition(position: String): Layers
            override fun addTo(map: Map): Layers
            override fun remove(): Layers
        }

        open class Scale(option: Option = definedExternally) : Control {
            interface Option : Control.Option {
                var maxWidth: Number
                var metric: Boolean
                var imperial: Boolean
                var updateWhenIdle: Boolean
            }

            override val option: Option

            override fun setPosition(position: String): Scale

            override fun addTo(map: Map): Scale
            override fun remove(): Scale

        }
    }
}


abstract external class Layer : Evented {

    interface Option {
        var pane: String
        var attribution: String
    }

    open val option: Option
    open fun addTo(map: Map): Layer
    open fun addTo(LayerGroup: LayerGroup): Layer
    open fun remove(): Layer
    open fun removeFrom(map: Map): Layer
    open fun removeFrom(LayerGroup: LayerGroup): Layer
    open fun getPane(): HTMLElement
    open fun getPane(name: String?): HTMLElement
    open fun getAttribution(): String?

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

abstract external class InteractiveLayer : Layer {
    interface Option : Layer.Option {
        var interactive: Boolean
        var bubblingMouseEvents: Boolean
    }

    override val option: Layer.Option
}


open external class ImageOverlay(
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

open external class VideoOverlay : ImageOverlay {
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

open external class SVGOverlay(
    url: String = definedExternally,
    bounds: LatLngBounds = definedExternally,
    option: Option = definedExternally
) : ImageOverlay {
    override fun getElement(): SVGElement
}

abstract external class GridLayer() : Layer {
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

open external class TileLayer(url: String = definedExternally) : GridLayer {
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

open external class Marker(latLng: LatLng = definedExternally, option: Option = definedExternally) : InteractiveLayer {
    interface Option : InteractiveLayer.Option {
        val icon: Icon
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
    open fun getIcon(): Icon
    open fun setIcon(value: Icon): Marker
    open fun getLatLng(): LatLng
    open fun setLatLng(latLng: LatLng): Marker
    open fun setZIndexOffset(offset: Number): Marker
    open fun setOpacity(opacity: Number): Marker

    override fun addTo(map: Map): Marker
    override fun addTo(LayerGroup: LayerGroup): Marker
    override fun remove(): Marker
    override fun removeFrom(map: Map): Marker
    override fun removeFrom(LayerGroup: LayerGroup): Marker


    override fun bindPopup(): Marker
    override fun bindPopup(popup: Popup): Marker
    override fun bindPopup(string: String): Marker
    override fun bindPopup(htmlElement: HTMLElement): Marker
    override fun unbindPopup(): Marker
    override fun openPopup(): Marker
    override fun openPopup(latLng: LatLng?): Marker
    override fun closePopup(): Marker
    override fun togglePopup(): Marker
    override fun isPopupOpen(): Marker
    override fun setPopupContent(string: String): Marker
    override fun setPopupContent(htmlElement: HTMLElement): Marker
    override fun setPopupContent(popup: Popup): Marker


    override fun bindTooltip(): Marker
    override fun bindTooltip(tooltip: Tooltip): Marker
    override fun bindTooltip(string: String): Marker
    override fun bindTooltip(htmlElement: HTMLElement): Marker
    override fun unbindTooltip(): Marker
    override fun openTooltip(): Marker
    override fun openTooltip(latLng: LatLng?): Marker
    override fun closeTooltip(): Marker
    override fun toggleTooltip(): Marker
    override fun isTooltipOpen(): Marker
    override fun setTooltipContent(string: String): Marker
    override fun setTooltipContent(htmlElement: HTMLElement): Marker
    override fun setTooltipContent(tooltip: Tooltip): Marker
}

open external class LayerGroup(array: Array<Layer> = definedExternally, option: Option = definedExternally) : Layer {

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

open external class FeatureGroup(layers: Array<Layer> = definedExternally, option: Option = definedExternally) :
    LayerGroup {
    open fun bringToFront(): FeatureGroup
    open fun bringToBack(): FeatureGroup
    open fun getBounds(): LatLngBounds
}

open external class GeoJSON(geoJson: Any = definedExternally, option: Option = definedExternally) : FeatureGroup {
    open fun addData(data: dynamic): GeoJSON
    open fun resetStyle(): GeoJSON
    open fun resetStyle(layer: dynamic): GeoJSON
    open fun setStyle(style: dynamic): GeoJSON
}

abstract external class Path : InteractiveLayer {

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

open external class Polyline(array: Array<LatLng> = definedExternally, option: Option = definedExternally) : Path {

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

open external class Polygon(array: Array<LatLng> = definedExternally, option: Option = definedExternally) : Polyline {

}

open external class Rectangle(latLngBounds: LatLngBounds = definedExternally, option: Option = definedExternally) :
    Polygon {
    open fun setBounds(bounds: LatLngBounds): Rectangle
}

open external class CircleMarker(latLng: LatLng = definedExternally) : Path {
    interface Option : Path.Option {
        val radius: Number
    }

    open fun getLatLng(): LatLng
    open fun setLatLng(latLng: LatLng): CircleMarker
    open fun getRadius(): Number
    open fun setRadius(radius: Number): CircleMarker
}

open external class Circle(latLng: LatLng = definedExternally, radius: Number = definedExternally) : CircleMarker {
    override fun getRadius(): Number
    override fun setRadius(radius: Number): CircleMarker
    open fun getBounds(): LatLngBounds
}

abstract external class Renderer : Layer {
    interface Option : Layer.Option {
        var padding: Number
        var tolerance: Number
    }

    override val option: Option

}

open external class SVG(option: Option = definedExternally) : Renderer
open external class Canvas(option: Option = definedExternally) : Renderer

abstract external class DivOverlay

open external class Popup() : DivOverlay {
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

open external class Tooltip() : DivOverlay {}
open external class Icon(option: Option = definedExternally) {
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

open external class DivIcon(option: Option = definedExternally) : Icon {
    interface Option : Icon.Option {
        var html: HTMLElement
        var bgPos: Boolean
    }

    override val option: Option

}

abstract external class CRS {

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

external class LatLng {
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

external class LatLngBounds {
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

external class Point(x: Number, y: Number, round: Boolean? = definedExternally) {

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

external class Bounds {
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

external class Transformation {
    constructor(a: Number, b: Number, c: Number, d: Number)
    constructor(array: Array<Number>)

    fun transform(point: Point, scale: Number = definedExternally): Point

    @JsName("untransform")
    fun unTransform(point: Point, scale: Number = definedExternally): Point
}

external object Browser {
    val ie: Boolean    //true for all Internet Explorer versions (not Edge).
    val ielt9: Boolean //true for Internet Explorer versions less than 9.
    val edge: Boolean //true for the Edge web browser.
    val webkit: Boolean //true for webkit-based browsers like Chrome and Safari (including mobile versions).
    val android: Boolean //true for any browser running on an Android platform.
    val android23: Boolean //true for browsers running on Android 2 or Android 3.
    val androidStock: Boolean //true for the Android stock browser (i.e. not Chrome)
    val opera: Boolean //	true for the Opera browser
    val chrome: Boolean //	true for the Chrome browser.
    val gecko: Boolean //	true for gecko-based browsers like Firefox.
    val safari: Boolean //	true for the Safari browser.
    val opera12: Boolean //	true for the Opera browser supporting CSS transforms (version 12 or later).
    val win: Boolean //	true when the browser is running in a Windows platform
    val ie3d: Boolean //	true for all Internet Explorer versions supporting CSS transforms.
    val webkit3d: Boolean //	true for webkit-based browsers supporting CSS transforms.
    val gecko3d: Boolean //	true for gecko-based browsers supporting CSS transforms.
    val any3d: Boolean //	true for all browsers supporting CSS transforms.
    val mobile: Boolean //	true for all browsers running in a mobile device.
    val mobileWebkit: Boolean //	true for all webkit-based browsers in a mobile device.
    val mobileWebkit3d: Boolean //	true for all webkit-based browsers in a mobile device supporting CSS transforms.
    val msPointer: Boolean    //true for browsers implementing the Microsoft touch events model (notably IE10).
    val pointer: Boolean    //true for all browsers supporting pointer events.
    val touch: Boolean    //true for all browsers supporting touch events. This does not necessarily mean that the browser is running in a computer with a touchscreen, it only means that the browser is capable of understanding touch events.
    val mobileOpera: Boolean    //true for the Opera browser in a mobile device.
    val mobileGecko: Boolean    //true for gecko-based browsers running in a mobile device.
    val retina: Boolean //	true for browsers on a high-resolution "retina" screen or on any screen when browser's display zoom is more than 100%.
    val passiveEvents: Boolean //true for browsers that support passive events.
    val canvas: Boolean    //true when the browser supports <canvas>.
    val svg: Boolean    //true when the browser supports SVG.
    val vml: Boolean
}


external interface Event<T> {
    var type: String
    var target: T
    var sourceTarget: Any
    var propagatedFrom: Any
}

external interface KeyboardEvent<T> : Event<T> {
    var originalEvent: org.w3c.dom.events.KeyboardEvent
}

external interface MouseEvent<T> : Event<T> {
    var latlng: LatLng
    var layerPoint: Point
    var containerPoint: Point
    var originalEvent: UIEvent
}

external interface LocationEvent<T> : Event<T> {
    var latlng: LatLng
    var bounds: LatLngBounds
    var accuracy: Number
    var altitude: Number
    var altitudeAccuracy: Number
    var heading: Number
    var speed: Number
    var timestamp: Number
}

external interface ErrorEvent<T> : Event<T> {
    var message: String
    var code: Number?
}

external interface LayerEvent<T> : Event<T> {
    val layer: Layer
}

external interface LayersControlEvent<T> : Event<T> {
    var layer: Layer
    var name: String
}

external interface TileEvent<T> : Event<T> {
    var tile: HTMLElement
    var coords: Point
}

external interface TileErrorEvent<T> : Event<T> {
    var tile: HTMLElement
    var coords: Point
    var error: dynamic
}

external interface ResizeEvent<T> : Event<T> {
    var odlSize: Point
    var newSize: Point
}

external interface GeoJSONEvent<T> : Event<T> {
    var layer: Layer
    var properties: Any
    var geometryType: String
    var id: String
}

external interface PopupEvent<T> : Event<T> {
    var popup: Popup
}

external interface TooltipEvent<T> : Event<T> {
    var tooltip: Tooltip
}

external interface DragEndEvent<T> : Event<T> {
    var distance: Number
}

external interface ZoomAnimEvent<T> : Event<T> {
    var center: LatLng
    var zoom: Number
    var noUpdate: Boolean
}
