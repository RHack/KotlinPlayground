package com.example.envoy.kotlinplayground

/**
 * Created by Envoy on 1/5/18.
 */
class CharacterStats{
    private val level = 0
    private val proficiencyBonus = level/5 + 2

    private val strength = 0
    private val constitution = 0
    private val dexterity = 0
    private val wisdom = 0
    private val intelligence = 0
    private val charisma = 0

    private val strengthSkills: Set<String> = setOf("Athletics", "Strength")
    private val constitutionSkills: Set<String> = setOf("Constitution")
    private val dexteritySkills: Set<String> = setOf("Acrobatics", "Dexterity", "Sleight of Hand", "Stealth")
    private val wisdomSkills: Set<String> = setOf("Animal Handling", "Insight", "Medicine", "Perception", "Survival", "Wisdom")
    private val intelligenceSkills: Set<String> = setOf("Arcana", "History", "Intelligence", "Investigation", "Nature", "Religion")
    private val charismaSkills: Set<String> = setOf("Charisma", "Deception", "Intimidation", "Performance", "Persuasion")

    private var trainedSkills: MutableList<String> = mutableListOf()

    private fun skillModifier(skill:String): Int {
        var skillModifier = 0

        if(trainedSkills.contains(skill)){
            skillModifier += proficiencyBonus
        }

        if(strengthSkills.contains(skill))
            skillModifier += abilityModifier(strength)
        else if (constitutionSkills.contains(skill))
            skillModifier += abilityModifier(constitution)
        else if (dexteritySkills.contains(skill))
            skillModifier += abilityModifier(dexterity)
        else if (wisdomSkills.contains(skill))
            skillModifier += abilityModifier(wisdom)
        else if (intelligenceSkills.contains(skill))
            skillModifier += abilityModifier(intelligence)
        else if (charismaSkills.contains(skill))
            skillModifier += abilityModifier(charisma)
        else
            return 0

        return skillModifier
    }

    private fun abilityModifier(ability:Int): Int {
        return ability/2 - 5
    }

}