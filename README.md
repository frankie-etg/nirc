# nIRC
IRC Bot Framework using netty I/O

##Getting Started

###Prerequisites

**This project runs on JDK 7+**

Download the [netty i/o](https://netty.io/) library and add it to your project

###Usage

**Connection variables are located in org.etg.nirc.Vars**
**Base plugin file in org.etg.nirc.plugin.Plugin**

Channel Operators (@) are stored in the ArrayList Vars.opList
Channel Half-Operators (%) are stored in the ArrayList Vars.halfOpList
List of all users in channel are stored in the ArrayList Vars.inIRC
IRC text styling codes are in the Vars class.

####Functions

*Get event source*
> p.getSource();

*Get event destination*
> p.getDestination();

*Get event text*
> p.getPayload();

*Send message*
> pf.getConnection().write("raw IRC input");

*Format message*
> pf.message(destination, message);