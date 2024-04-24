#Create a simulator object
set ns [new Simulator]

#Visualization using NAM
set tf [open out.nam w]
$ns namtrace-all $tf

#Create nodes
set node0 [$ns node]
set node1 [$ns node]
set node2 [$ns node]
set node3 [$ns node]
set node4 [$ns node]

#Create links
$ns duplex-link $node0 $node1 1Mb 10ms DropTail
$ns duplex-link $node0 $node2 1Mb 10ms DropTail
$ns duplex-link $node2 $node3 1Mb 10ms DropTail
$ns duplex-link $node1 $node3 1Mb 10ms DropTail
$ns duplex-link $node3 $node4 1Mb 10ms DropTail

#Set up routing
$ns rtproto DV

#Set up traffic flow
set udp [new Agent/UDP]
$ns attach-agent $node0 $udp
set null1 [new Agent/Null]
set null2 [new Agent/Null]
$ns attach-agent $node3 $null1
$ns attach-agent $node4 $null2
set cbr [new Application/Traffic/CBR]
$cbr set packetSize_ 500
$cbr set interval_ 0.5
$cbr attach-agent $udp
$ns at 0.1 "$cbr start"
$ns at 4.0 "$cbr stop"

#Run simulation
$ns run

exec nam out.nam &