package com.bytelegend.client.app.ui

import com.bytelegend.client.app.engine.util.jsObjectBackedSetOf
import kotlinx.html.classes
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.img
import react.dom.jsStyle
import react.dom.span

const val STAR_SVG_XML =
    "data:image/svg+xml,%3Csvg height='511pt' viewBox='0 -10 511.987 511' width='511pt' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M510.652 185.902a27.158 27.158 0 0 0-23.425-18.71l-147.774-13.419-58.433-136.77C276.71 6.98 266.898.494 255.996.494s-20.715 6.487-25.023 16.534l-58.434 136.746-147.797 13.418A27.208 27.208 0 0 0 1.34 185.902c-3.371 10.368-.258 21.739 7.957 28.907l111.7 97.96-32.938 145.09c-2.41 10.668 1.73 21.696 10.582 28.094 4.757 3.438 10.324 5.188 15.937 5.188 4.84 0 9.64-1.305 13.95-3.883l127.468-76.184 127.422 76.184c9.324 5.61 21.078 5.097 29.91-1.305a27.223 27.223 0 0 0 10.582-28.094l-32.937-145.09 111.699-97.94a27.224 27.224 0 0 0 7.98-28.927zm0 0' fill='%23ffc107'/%3E%3C/svg%3E"
const val HOLLOW_STAR_SVG_XML =
    "data:image/svg+xml,%3Csvg height='511pt' viewBox='0 -10 511.987 511' width='511pt' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M114.594 491.14c-5.61 0-11.18-1.75-15.934-5.187a27.223 27.223 0 0 1-10.582-28.094l32.938-145.09L9.312 214.81a27.188 27.188 0 0 1-7.976-28.907 27.208 27.208 0 0 1 23.402-18.71l147.797-13.419L230.97 17.027C235.277 6.98 245.089.492 255.992.492s20.715 6.488 25.024 16.512l58.433 136.77 147.774 13.417c10.882.98 20.054 8.344 23.425 18.711 3.372 10.368.254 21.739-7.957 28.907L390.988 312.75l32.938 145.086c2.414 10.668-1.727 21.7-10.578 28.098-8.832 6.398-20.61 6.89-29.91 1.3l-127.446-76.16-127.445 76.203c-4.309 2.559-9.11 3.864-13.953 3.864zm141.398-112.874c4.844 0 9.64 1.3 13.953 3.859l120.278 71.938-31.086-136.942a27.21 27.21 0 0 1 8.62-26.516l105.473-92.5-139.543-12.671a27.18 27.18 0 0 1-22.613-16.493L255.992 39.895 200.844 168.96c-3.883 9.195-12.524 15.512-22.547 16.43L38.734 198.062l105.47 92.5c7.554 6.614 10.858 16.77 8.62 26.54l-31.062 136.937 120.277-71.914c4.309-2.559 9.11-3.86 13.953-3.86zm-84.586-221.848s0 .023-.023.043zm169.13-.063l.023.043c0-.023 0-.023-.024-.043zm0 0'/%3E%3C/svg%3E"

const val STAR_PNG_BASE64 =
    "data:image/png;base64,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"
const val HOLLOW_STAR_PNG_BASE64 =
    "data:image/png;base64,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"

interface TitleStarCounterProps : RProps {
    var total: Int
    var current: Int
}

class TitleStarCounter : RComponent<TitleStarCounterProps, RState>() {
    override fun RBuilder.render() {
        if (props.total > 5) {
            span {
                attrs.classes = jsObjectBackedSetOf("map-title-text")
                +"${props.current}/${props.total}"
            }
            star(STAR_PNG_BASE64)
        } else {
            repeat(props.current) {
                star(STAR_PNG_BASE64)
            }
            repeat(props.total - props.current) {
                star(HOLLOW_STAR_PNG_BASE64)
            }
        }
    }

    private fun RBuilder.star(src: String) {
        img {
            attrs.jsStyle {
                width = "24px"
                height = "24px"
                display = "inline-block"
            }
            attrs.src = src
        }
    }
}
