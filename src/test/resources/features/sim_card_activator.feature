Feature: SIM Card Activation

  Scenario: Successful SIM card activation
    Given I have a SIM card with ICCID "1255789453849037777" and email "test@client.com"
    When I send an activation request
    Then the SIM card with id 1 should be active

  Scenario: Failed SIM card activation
    Given I have a SIM card with ICCID "8944500102198304826" and email "test2@client.com"
    When I send an activation request
    Then the SIM card with id 2 should not be active
