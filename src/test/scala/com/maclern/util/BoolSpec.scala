package com.maclern.util

import org.scalatest.{FlatSpec, Matchers}

class BoolSpec extends FlatSpec with Matchers {
    
    "Bool" should "imply a function if true" in {
        intercept[RuntimeException] {
            Bool(true).implies(() => throw new RuntimeException)
        }
        intercept[RuntimeException] {
            Bool(true)->(() => throw new RuntimeException)
        }
    }
    
    "Bool" should "not imply a function if false" in {
        Bool(false).implies(() => fail("Did not expect implication"))
        Bool(false)->(() => fail("Did not expect implication"))
    }
    
    "Bool" should "chain calls together" in {
        Bool(false)
            .implies(
                () => fail("Did not expect implication")
            )->(
                () => fail("Did not expect implication")
            )
    }
    
}
