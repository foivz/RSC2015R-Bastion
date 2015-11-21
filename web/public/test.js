$(document).ready( function() {

    /*
    var map;
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 8
        });
    }
     <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB_VJESZUU2Dqfatb_5YPr1U5H8bv7oY_o&callback=initMap"
     async defer></script>
    */
    var iconType = 1;
    var markers = [];
    var map;
    var bangalore = { lat: 46.3087404, lng: 16.3525307 };
    var rectangle;
    var bounds = {
        north: 46.3036392,
        south: 46.3006404,
        east: 16.3515227,
        west: 16.3448307
    };


    function initMap(){
         map = new google.maps.Map(document.getElementById('map'), {
            zoom: 15,
            center: bangalore
        });


        // This event listener calls addMarker() when the map is clicked.
        google.maps.event.addListener(map, 'click', function(event) {
            addMarker(event.latLng, map);
        });

        // Add a marker at the center of the map.
        //addMarker(bangalore, map);
        //iconType = 1;

    }
    initMap();

    function addMarker(location, map) {
        // Add the marker at the clicked location, and add the next-available label
        // from the array of alphabetical characters.
        //http://maps.google.com/mapfiles/kml/pal2/icon13.png
        if(iconType == 1){
            var iconBase = 'http://maps.google.com/mapfiles/kml/pal3/icon48.png';
        }
        else if(iconType == 2){
            var iconBase = 'http://maps.google.com/mapfiles/kml/pal2/icon4.png';
        }
        else if(iconType == 3){
            var iconBase = 'http://maps.google.com/mapfiles/kml/paddle/blu-circle-lv.png';
        }
        else if(iconType == 4){
            var iconBase = 'http://maps.google.com/mapfiles/kml/paddle/red-circle-lv.png';
        }
        else if(iconType == 5){
            var iconBase = 'http://maps.google.com/mapfiles/kml/pal2/icon13.png';
        }

        var marker = new google.maps.Marker({
            position: location,
            map: map,
            icon: iconBase
        });
        marker.type = iconType;
        markers.push(marker);

    }

    function setMapOnAll(map){
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(map);
        }
    }

    function clearMarkers(){
        setMapOnAll(null);
        markers=[];
    }

    $("#borders" ).click(function() {

        rectangle = new google.maps.Rectangle({
            bounds: bounds,
            editable: true
        });
        google.maps.event.addListener(rectangle, 'click', function(event) {
            addMarker(event.latLng, map);
        });
        rectangle.setMap(map);
    });


    $("#borders2" ).click(function() {
        var ne = (rectangle.getBounds().getNorthEast());
        var sw = rectangle.getBounds().getSouthWest();
        bounds["north"] = ne.lat();
        bounds["east"] = ne.lng();
        bounds["south"] = sw.lat();
        bounds["west"] = sw.lng();

        rectangle.setMap(null);
    });

    $("#borders3" ).click(function() {
        iconType = 1;
    });
    $("#borders4" ).click(function() {
        iconType = 2;
    });
    $("#borders5" ).click(function() {
        iconType = 3;
    });
    $("#borders6" ).click(function() {
        /*
        for (var i = 0; i < markers.length; i++) {
            alert(markers[i].type);
            alert(markers[i].position.lat());
        }
        */
        iconType = 4;
    });

    $("#borders7" ).click(function() {
        //savanej
    });
    $("#borders8" ).click(function() {
        iconType = 1;
        clearMarkers();
        initMap();
    });

    $("#borders9" ).click(function() {
        iconType = 5;
    });

    /*
        function addMarker(pos){
            var image = 'https://google-developers.appspot.com/maps/documentation/javascript/examples/full/images/beachflag.png';
            var beachMarker = new google.maps.Marker({
                position: pos,
                map: map,
                icon: image
            });
        }
        addMarker({lat: -33.890, lng: 151.274});
    */


});