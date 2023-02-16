package lotto.model.generator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {

    @Test
    fun `랜덤 숫자 6개를 발생시킨다`() {
        assertThat(
            LottoGenerator().generate().size
        ).isEqualTo(6)
    }
}
