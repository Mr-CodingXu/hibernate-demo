package com.coding.demo.hibernate.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "console_trader")
open class Trader {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(name = "NAME_")
    var name: String? = null

    @Column(name = "USERNAME")
    var username: String? = null

    @Column(name = "NO_")
    var no: Long? = null

    @Column(name = "PASSWORD")
    var password: String? = null

    @Column(name = "STATE")
    var state: Int? = null

    @Column(name = "VERSION")
    var version: Int? = null

    @Column(name = "CREATED_BY")
    var createBy: String? = null

    @Column(name = "CREATED_DATE")
    var createDate: Date? = null

    @Column(name = "LAST_MODIFIED_BY")
    var lastModifiedBy: String? = null

    @Column(name = "LAST_MODIFIED_DATE")
    var lastModifiedDate: Date? = null
}
