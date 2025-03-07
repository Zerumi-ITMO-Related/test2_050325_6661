package io.github.zerumi

import java.io.File
import kotlin.math.PI
import kotlin.math.pow

fun sin(x: Double) = (-(x % (2 * PI)) + PI).run {
    this - this.pow(3) / (3 * 2 * 1) + this.pow(5) / (5 * 4 * 3 * 2 * 1) -
            this.pow(7) / (7 * 6 * 5 * 4 * 3 * 2 * 1) + this.pow(9) / (9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1)
}

fun cos(x: Double) = sin(PI / 2 - x)

fun tan(x: Double) = sin(x) / cos(x)

fun cot(x: Double) = 1.0 / tan(x)

fun sec(x: Double) = 1.0 / cos(x)

fun csc(x: Double) = 1.0 / sin(x)

fun ln(x: Double) = ((x - 1.0) / (x + 1.0)).run {
    2 * (this + this.pow(3) / 3 + this.pow(5) / 5 + this.pow(7) / 7 + this.pow(9) / 9)
}.also { require(x > 0) }

fun log_3(x: Double) = ln(x) / ln(3.0)

fun log_5(x: Double) = ln(x) / ln(5.0)

fun log_10(x: Double) = ln(x) / ln(10.0)

fun f(x: Double) = if (x <= 0)
    (((((tan(x).pow(3)) + sec(x)) - csc(x)) / (cot(x) * (csc(x) * (cot(x) / csc(x))))).pow(3))
else
    (((((log_5(x) - log_5(x)).pow(3)) - (log_3(x) * log_5(x))) + log_10(x)) / ((log_3(x) * log_10(x)) * (((log_5(x) -
            log_10(x)).pow(2)) * log_10(x))))


fun generateCsv(filename: String, module: (Double) -> Double, start: Double, step: Double, count: Int) =
    File(filename).bufferedWriter().use { writer ->
        writer.write("X, Result\n")
        var x = start
        repeat (count) {
            writer.write("$x, ${module(x)}\n")
            x += step
        }
    }

fun main() {
    generateCsv("prog_out/sin.csv", ::sin, 0.0, 0.25, 10)
    generateCsv("prog_out/cos.csv", ::cos, 0.0, 0.25, 10)
    generateCsv("prog_out/tan.csv", ::tan, 0.0, 0.25, 10)
    generateCsv("prog_out/cot.csv", ::cot, 0.0, 0.25, 10)
    generateCsv("prog_out/sec.csv", ::sec, 0.0, 0.25, 10)
    generateCsv("prog_out/csc.csv", ::csc, 0.0, 0.25, 10)
    generateCsv("prog_out/ln.csv", ::ln, 0.1, 0.25, 10)
    generateCsv("prog_out/log_3.csv", ::log_3, 0.1, 0.25, 10)
    generateCsv("prog_out/log_5.csv", ::log_5, 0.1, 0.25, 10)
    generateCsv("prog_out/log_10.csv", ::log_10, 0.1, 0.25, 10)
    generateCsv("prog_out/f-small.csv", ::f, -1.0, 0.1, 20)
    generateCsv("prog_out/f-large.csv", ::f, -10.0, 0.5, 40)
}
