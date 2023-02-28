package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValueClassStudyTest {

    @Test
    fun `value class의 동등성 검사1`() {
        val valueClass1: ValueClass = ValueClass(5)
        val valueClass2: ValueClass = ValueClass(5)

        assertThat(valueClass1).isEqualTo(valueClass2)
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
