package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {

    @Test
    fun `1~45의 랜덤번호 6개를 추출한다`() {
        // when
        val actual = ShuffledLottoGenerator().generate()

        assertThat(actual.size).isEqualTo(6)
    }
}
