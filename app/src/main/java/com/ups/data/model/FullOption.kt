package com.ups.data.model

data class FullOption(
    val trim_price: String,
    val trim_sortorder: String,
    val trim_spec: List<TrimSpec>
)