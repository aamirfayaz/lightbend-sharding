package com.reactivebbq.orders

import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, property = "type")
trait SerializableMessage

/**
  A Marker trait used by the serializer in order to determine what type of serializer to use for certain types of
  messages types essentially.

 */
