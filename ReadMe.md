# GoJek Parking lot

###How to run
* From parking_lot directory run below commands
*./bin/setup
*./bin/parking_lot (For interactive mode)
*./bin/parking_lot /Users/abc/source/parking-lot-1.4.2/parking_lot/functional_spec/fixtures/file_input.txt
 (For file based  mode)
 
 
## Details
* The idea behind implementation was to push to for clean design and domain separations
* Intentionally did not optimize lookups by color, slot by using structures to optimize lookup to O(lg N)
* Current implementation has complexity of O(N) for lookups 

