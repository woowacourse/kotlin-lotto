package lotto.model

import lotto.entity.Game
import lotto.entity.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class LottoGeneratorTest {
    private lateinit var lottoGenerator: LottoGenerator

    @BeforeAll
    fun init() {
        val game = Game(
            listOf(
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
            )
        )
        lottoGenerator = SequentialLottoNumberGenerator(game)
    }

    @Test
    fun `생성된 로또는 1,2,3,4,5,6이다`() {
        val lotto = lottoGenerator.generate()
        assertThat(lotto.numbers == setOf(1, 2, 3, 4, 5, 6)).isTrue
    }
}
