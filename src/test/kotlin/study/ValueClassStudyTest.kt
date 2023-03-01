package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValueClassStudyTest {

    @Test
    fun `value_class의_동등성_검사1`() {
        val valueClass1: ValueClass = ValueClass(5)
        val valueClass2: ValueClass = ValueClass(5)

        assertThat(valueClass1).isEqualTo(valueClass2)
    }

    @Test
    fun `value_class의_동등성_검사2`() {
        val valueClass1: ValueClass = ValueClass(5)
        val valueClass2: ValueClass = ValueClass(5)

        assertThat(valueClass1 == valueClass2).isTrue()
    }

    @Test
    fun `value_class_출력값_확인`() {
        val valueClass1: ValueClass = ValueClass(1000)
        val valueClass2: ValueClass = ValueClass(1000)
        val a: Int = 1000
        println(valueClass1.hashCode())
        println(valueClass2.hashCode())
        println(a.hashCode())
    }

    @Test
    fun `int의_동등성_검사1`() {
        val int1: Int = 5
        val int2: Int = 5

        assertThat(5).isEqualTo(5)
    }

    @Test
    fun `int의_동등성_검사2`() {

        assertThat(1).isSameAs(1)
    }

    // === 불가
    // @Test
    // fun `value class의 동등성 검사2` () {
    //     val valueClass1: ValueClass = ValueClass(5)
    //     val valueClass2: ValueClass = ValueClass(5)
    //
    //     assertThat(valueClass1).isSameAs(valueClass2)
    // }
}

@JvmInline
value class ValueClass(val value: Int)
