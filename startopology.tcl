#Create a new simulator instance
set ns [new Simulator]

#Create 10 nodes
set no_of_nodes 10
for {set i 0} {$i < $no_of_nodes} {incr i} {
    set node($i) [$ns node]
}

#Create links from all nodes to the central node
for {set i 1} {$i < $no_of_nodes} {incr i} {
    $ns duplex-link $node($i) $node(0) 10Mb 10ms DropTail
}

#Set up routing protocol
$ns rtproto Static

#Enable Tracing
set tracefile [open out.tr w]
$ns trace-all $tracefile

$ns run

#Visualize with nam
exec nam out.tr &
