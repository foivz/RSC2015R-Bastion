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
    var markersHelp = [];
    var map;
    var bangalore = { lat: 46.3087404, lng: 16.3525307 };
    var rectangle;



    function initMap(){
        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 15,
            center: bangalore
        });


        // This event listener calls addMarker() when the map is clicked.

        // Add a marker at the center of the map.
        //addMarker(bangalore, map);
        //iconType = 1;






    }


    function addMarker(location, map,ulaz) {
        // Add the marker at the clicked location, and add the next-available label
        // from the array of alphabetical characters.
        //http://maps.google.com/mapfiles/kml/pal2/icon13.png

        if(ulaz == 1){
            var iconBase = 'http://maps.google.com/mapfiles/kml/pal3/icon48.png';
        }
        else if(ulaz == 2){
            var iconBase = 'http://maps.google.com/mapfiles/kml/pal2/icon4.png';
        }
        else if(ulaz == 3){
            var iconBase = 'http://maps.google.com/mapfiles/kml/paddle/blu-circle-lv.png';
        }
        else if(ulaz == 4){
            var iconBase = 'http://maps.google.com/mapfiles/kml/paddle/red-circle-lv.png';
        }
        else if(ulaz == 5){
            var iconBase = 'http://maps.google.com/mapfiles/kml/pal2/icon13.png';
        }
        else if(ulaz == 69){
            var iconBase = 'http://maps.google.com/mapfiles/kml/paddle/blu-circle.png';
        }
        else if(ulaz == 70){
            var iconBase = 'http://maps.google.com/mapfiles/kml/paddle/stop.png';
        }

        var marker = new google.maps.Marker({
            position: location,
            map: map,
            icon: iconBase
        });
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

    function createRectangle(bounds){
        if(!rectangle){
            rectangle = new google.maps.Rectangle({
                bounds: bounds
            });
            rectangle.setMap(map);
        }

    }

    function destroyRectangle(){

        rectangle.setMap(null);
    }

    initMap();
    setInterval(function(){
        //$('#map').empty();
        //initMap();
        clearMarkers();
        $idTima = $('#idTima').val();
        $.ajax({
            type: "POST",
            url: "/viewMy",
            async:false,
            dataType: "json",
            success: function (data, status, xhr) {
                var bounds = {
                    north: parseFloat(data.map.border_north),
                    south: parseFloat(data.map.border_south),
                    east: parseFloat(data.map.border_east),
                    west: parseFloat(data.map.border_west)
                };
                createRectangle(bounds);
                /*
                 rectangle = new google.maps.Rectangle({
                 bounds: bounds
                 });

                 rectangle.setMap(map);
                 */
                //var myLatLng = {lat: -25.363, lng: 131.044};
                /*
                 for each(var marker in data.markers){
                 var myLat = {lat: marker.pivot.lat, lng: marker.pivot.long};
                 //addMarker(myLat,map,marker.type);

                 }
                 */
                for(var i = 0;i<data.markers.length;i++){
                    var myLatlng = new google.maps.LatLng(data.markers[i].pivot.lat, data.markers[i].pivot.long);
                    //var myLat = {lat: data.markers[i].pivot.lat, lng : data.markers[i].pivot.long};
                    addMarker(myLatlng,map,data.markers[i].type);

                }
                //createRectangle(bounds);
                for(var j = 0;j<data.teams.length;j++){

                    for(var z = 0; z < data.teams[j].players.length;z++){
                        if(data.teams[j].players[z].status == 0){
                            var myLatlng = new google.maps.LatLng(data.teams[j].players[z].lat, data.teams[j].players[z].long);
                            if(j == 0){
                                addMarker(myLatlng,map,69);
                                //blue
                            }
                            else if(j == 1){
                                addMarker(myLatlng,map,70);
                            }
                        }


                    }
                }


            },

            error: function (jqXHR, status) {
                //alert(status);
            }
        });
    }, 5000);



});