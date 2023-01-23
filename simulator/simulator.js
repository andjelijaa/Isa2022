var gps = require('gps-simulator/gps-simulator.js');
var gpsData = require('gps-simulator/gps-simulator-data.js');


console.log(gpsData.routes[0].AB);  

var busId = 'Bus 01';

var gpsSimulator = new gps.GpsSimulator(gpsData.routes[0].AB, busId, 1000, 1);

gpsSimulator.start(function(position, beStopped, movableObject, currentRouteIndex) {
	
	var str = "Route " + currentRouteIndex + ", speed " + movableObject.velocity * 10 + " km/h";
	console.log('[ ' + new Date() + ' ] ' + str);
	
	var gps_sensor = {
		"device_id" : busId,
		"timestamp" : new Date(),
		"latitude" : position.latitude,
		"longitude" : position.longitude
	};
    console.log(gps_sensor);
	
	// gpsSimulator.sendData(gps_sensor);
	
	if (beStopped == true) {
		console.log(busId + ' has been stopped');
		console.log('======================================');
        
	}
});

// gpsSimulator.stop((position, beStopped, movableObject, currentRouteIndex) => {
//     if(position.latitude === 21.00816){
//         beStopped = true;
//     }
// })
