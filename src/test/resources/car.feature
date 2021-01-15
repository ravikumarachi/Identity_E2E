Feature:

  Scenario:
    Given read text file "car_input.txt"
    Then I entered registration numbers which are extracted from the text file and compare with output file "car_output.txt"