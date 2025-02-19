package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SetStudy {
    @Test
    fun `Set을 출력할 때 어떤 식으로 나올지 확인`() {
        val set1 = setOf(5, 4, 3, 2, 1)
        assertThat(set1.toString()).isEqualTo("[5, 4, 3, 2, 1]")

        val set2 = setOf(1, 2, 3, 4, 5)
        assertThat(set2.toString()).isEqualTo("[1, 2, 3, 4, 5]")
    }
}
