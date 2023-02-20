package domain.lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import util.PREFIX

class LottoTest {
    @Test
    fun `로또 번호가 6개가 아니라면 에러 발생 (5개일때)`() {
        // given

        // when
        val exception = runCatching { Lotto(1, 2, 3, 4, 5) }.exceptionOrNull()

        // then
        assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(exception).hasMessageContaining("$PREFIX 로또번호는 6개여야합니다.")
    }

    @Test
    fun `로또 번호가 6개가 아니라면 에러 발생 (7개일때)`() {
        // given

        // when
        val exception = runCatching { Lotto(1, 2, 3, 4, 5, 6, 7) }.exceptionOrNull()

        // then
        assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
        assertThat(exception).hasMessageContaining("$PREFIX 로또번호는 6개여야합니다.")
    }
}
