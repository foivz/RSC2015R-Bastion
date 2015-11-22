/**
 * Created by jtrupina on 22.11.15..
 */

function visitorData (data) {

    $('#chart1').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Most used maps'
        },
        xAxis: {

        },
        yAxis: {
            title: {
                text: 'Number of times'
            }
        },
        series: []
    });
}
$(document).ready(function() {
    alert("test");
    $.ajax({
        url: '/popularmaps',
        type: 'GET',
        async: true,
        dataType: "json",
        success: function (data) {
            visitorData(data);
        }
    });
});
