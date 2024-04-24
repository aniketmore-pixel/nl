#Initializing the network
set ns [new Simulator]
$ns rtproto LS

#Create five nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]

#Create trace & nam files
set tf [open out.tr w]
$ns trace-all $tf
set nf [open out.nam w]
$ns namtrace-all $nf

#Labeling the nodes & giving them colour
$n0 label "node 0"
$n1 label "node 1"
$n2 label "node2"
$n3 label "node 3"
$n4 label "node 4"
$n0 label-color red
$n1 label-color red
$n2 label-color blue
$n3 label-color blue
$n4 label-color red

#Create links between nodes
$ns duplex-link $n0 $n1 1.5Mb 10ms DropTail
$ns duplex-link $n1 $n2 1.5Mb 10ms DropTail
$ns duplex-link $n2 $n3 1.5Mb 10ms DropTail
$ns duplex-link $n3 $n4 1.5Mb 10ms DropTail
$ns duplex-link $n4 $n0 1.5Mb 10ms DropTail

#Orient the links between nodes
$ns duplex-link-op $n0 $n1 orient left-down
$ns duplex-link-op $n1 $n2 orient right-down
$ns duplex-link-op $n2 $n3 orient right
$ns duplex-link-op $n3 $n4 orient right-up
$ns duplex-link-op $n4 $n0 orient left-up

#Attaching TCP Agents 
set tcp [new Agent/TCP]
$ns attach-agent $n0 $tcp
set sink1 [new Agent/TCPSink]
$ns attach-agent $n2 $sink1
$ns connect $tcp $sink1
set sink2 [new Agent/TCPSink]
$ns attach-agent $n3 $sink2
$ns connect $tcp $sink2

#Creating FTP Traffic 
set traffic_ftp [new Application/FTP]
$traffic_ftp attach-agent $tcp

#Adding a finish procedure
proc finish {} {
    global ns,nf
	$ns flush-trace
	close $nf 
	exec nam out.nam &
    exit 0	
}

$ns at 0.5 "traffic_ftp start"
$ns rt-model at 1.0 down $n2 $n3
$ns rt-model at 2.0 up $n2 $n3
$ns at 3.0 "traffic_ftp start"
$ns at 4.0 "traffic_ftp stop"
$ns at 5.0 "finish"
$ns run 

