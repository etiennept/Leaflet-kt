import org.w3c.dom.HTMLElement

external object L {
    abstract class Class {

    }

    abstract class Evented : Class {
        internal fun on()
        internal fun off()
        internal fun once()
        internal fun fire()
        internal fun listens(type: String): Boolean
    }
    abstract class Control : Class {
        fun getPosition(): String
        fun setPosition(position: String): Control
        fun getContainer(): HTMLElement
        fun addTo(map: Map): Control
        fun remove(): Control

        companion object {
            open class Zoom : Control
            open class Attribution : Control
            open class Layers : Control
            open class Scale : Control
        }
    }

    abstract class Handler : Class {
        fun enable ()  : Handler
        fun disable() :Handler
        fun enabled () : Boolean
    }


    abstract class Layer : Evented {
        fun addTo(map: Map): Layer
        fun addTo(LayerGroup :LayerGroup)
        fun remove(): Layer
        fun removeFrom( map : Map )
        fun removeFrom(LayerGroup: LayerGroup)
        fun getPane(): HTMLElement
        fun getPane(name: String?): HTMLElement
        fun getAttribution(): String

    }




    open class Marker (latLng:LatLng) : Layer {
        fun getLatLng() : LatLng
        fun setLatLng( latLng: LatLng) : Marker
        fun setZIndexOffset( offset : Number ) :Marker
        fun setOpacity(opacity: Number ) : Marker
    }


    abstract class GridLayer : Layer {
        fun bringToFront( ) :GridLayer
        fun bringToBack() : GridLayer
        fun getContainer () : HTMLElement
        fun setOpacity( opacity : Number) : GridLayer
        fun setZIndex(index : Number) :GridLayer
        fun isLoading() : Boolean
        fun redraw() :GridLayer
        //fun getTileSize()
    }

    open class TileLayer(url: String) : GridLayer {
        fun setUrl(url: String): TileLayer
        fun setUrl(url: String, noRedraw: Boolean): TileLayer

        //fun getTileUrl() : String
    }

    open class LayerGroup ( array: Array<Layer>    )  {
        fun addLayer (Layer: Layer) :LayerGroup
        fun removeLayer(Layer: Layer) :LayerGroup
        fun removeLayer( id :Number) :LayerGroup
        fun hasLayer(id :Number):Boolean
        fun hasLayer( Layer: Layer):Boolean
        fun clearLayers () : LayerGroup
        fun toGeoJSON(id : Number) :Any
        fun eachLayer (  on : (Layer) -> Unit )
        fun getLayer( id : Number)
        fun getLayers () : Array<Layer>
        fun getLayerId( Layer: Layer) : Number
    }
    abstract class DivOverlay{

    }



    open class Map : Evented {
        constructor(htmlElement: HTMLElement)
        constructor(id: String)

        fun setView(latLng: LatLng, zoom: Number): Map
        fun getCenter(): LatLng
        fun getZoom(): Number
        fun getMinZoom(): Number
        fun getMaxZoom(): Number

        fun addControl(control: Control): Map
        fun removeControl(control: Control): Map
        fun addLayer(Layer: Layer): Map
        fun removeLayer(Layer: Layer): Map
        fun hasLayer(Layer: Layer): Boolean
        fun setMinZoom(int: Number): Map
        fun setMaxZoom(int: Number): Map

        //fun addHandler( handler: Handler) : Map
        fun distance(latlng1: LatLng, latlng2: LatLng): Double

        companion object {

        }
    }



    class LatLng{
        constructor(lat: Number, lng: Number)
        constructor(lat: Number , lng: Number , alt : Number)
        constructor()
        constructor(array: Array<Number>)


        var lat: Number
        var lng: Number
        fun wrap(): LatLng
        fun distanceTo(otherLatLng: LatLng): Number
        fun equals(otherLatLng: LatLng, maxMargin: Number): Boolean
        override fun equals(other: Any?): Boolean
        override fun toString(): String
    }


}

