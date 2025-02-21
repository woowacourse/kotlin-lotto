package lotto.service

import lotto.domain.model.Lotto
import lotto.domain.service.LottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {
    @Test
    fun `로또 객체를 생성한다`() {
        assertThat(LottoGenerator().generateLotto()).isExactlyInstanceOf(Lotto::class.java)
    }
}
