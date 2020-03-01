#!/bin/sh

SCF_CONDITION_1="SCF Done:  E(RPW91-PW91)"
SCF_CONDITION_2="SCF Done:  E(UPW91-PW91)"
EXCITED_CONDITION="Excited State   1:  "
NM_STR="nm"
EIGEN_OCC_CONDITION="Alpha  occ. eigenvalues"
EIGEN_VIRT_CONDITION="Alpha virt. eigenvalues"
NUMBER_RE="^[-+]?[0-9]+\.?[0-9]*$"
EIGEN_CONSTANT=27.2114

get_values() {
    set_scf_result
    set_excited_result
    set_eigen_values
    set_virt_values
    echo "${SCF_RESULT}|${EXCITED_RESULT}|${EIGEN_OCC_RESULT}|${EIGEN_VIRT_RESULT}" >> ${OUTPUT_FILE_NAME}
}

set_scf_result() {
    SCF_RESULT=$(grep "$SCF_CONDITION_1" ${FILE_NAME} | awk '{ printf $5 "(RPW91)" }')
    if [[ -z ${SCF_RESULT} ]]
    then
        SCF_RESULT=$(grep 'SCF Done:  E(UPW91-PW91)' APA-2.txt | awk '{ printf $5 "(UPW91)" }')
    fi

    if [[ -z ${SCF_RESULT} ]]
    then
        echo "${FILE_NAME} Error: cannot get SCF_RESULT"
        exit 1
    fi
}

set_excited_result() {
    EXCITED_RESULT=$(grep "$EXCITED_CONDITION" ${FILE_NAME} | awk '{ for(i=1; i<=NF; i++) print $i; }')

    # convert to an array
    EXCITED_RESULT_ARR=(${EXCITED_RESULT})
    SELECTED_INDEX=0
    for i in ${EXCITED_RESULT_ARR[@]}
    do
        if [[ ${i} == ${NM_STR} ]]
        then
            # get index where "nm" exist
            break
        fi
        ((SELECTED_INDEX++))
    done

    # result set
    ((SELECTED_INDEX=SELECTED_INDEX-1))
    EXCITED_RESULT=${EXCITED_RESULT_ARR[SELECTED_INDEX]}

    # check if number using regular expression
    if ! [[ ${EXCITED_RESULT} =~ ${NUMBER_RE} ]]
    then
       echo "${FILE_NAME} Error: EXCITED_RESULT is not a number"
       exit 1
    fi
}

set_eigen_values() {
    EIGEN_OCC_RESULT=$(grep "$EIGEN_OCC_CONDITION" ${FILE_NAME})

    SAVEIFS=${IFS} # Save current IFS
    IFS=$'\n' # Change IFS to new line
    EIGEN_OCC_RESULT_LINES=(${EIGEN_OCC_RESULT}) # split to array
    IFS=${SAVEIFS} # Restore IFS

    EIGEN_OCC_RESULT=""
    for i in ${EIGEN_OCC_RESULT_LINES[${#EIGEN_OCC_RESULT_LINES[@]}-1]}
    do
        NO_WHITESPACE_NUM="$(echo "${i}" | tr -d '[:space:]')"
        if [[ ${NO_WHITESPACE_NUM} =~ ${NUMBER_RE} ]]
        then
            EIGEN_OCC_RESULT="${NO_WHITESPACE_NUM}"
            EIGEN_OCC_RESULT=$(echo ${EIGEN_OCC_RESULT}*${EIGEN_CONSTANT} | bc -l | awk '{printf "%.12f", $0}')
        fi
    done

    # check if number using regular expression
    if ! [[ ${EIGEN_OCC_RESULT} =~ ${NUMBER_RE} ]]
    then
       echo "${FILE_NAME} Error: EIGEN_OCC_RESULT is not a number"
       exit 1
    fi
}


set_virt_values() {
    EIGEN_VIRT_RESULT=$(grep "$EIGEN_VIRT_CONDITION" ${FILE_NAME})

    SAVEIFS=${IFS} # Save current IFS
    IFS=$'\n' # Change IFS to new line
    EIGEN_VIRT_RESULT_LINES=(${EIGEN_VIRT_RESULT}) # split to array
    IFS=${SAVEIFS} # Restore IFS

    EIGEN_VIRT_RESULT=""
    for i in ${EIGEN_VIRT_RESULT_LINES[0]}
    do
        NO_WHITESPACE_NUM="$(echo "${i}" | tr -d '[:space:]')"
        if [[ ${NO_WHITESPACE_NUM} =~ ${NUMBER_RE} ]]
        then
#            echo "selected ${i}"
            EIGEN_VIRT_RESULT="${NO_WHITESPACE_NUM}"
            EIGEN_VIRT_RESULT=$(echo ${EIGEN_VIRT_RESULT}*${EIGEN_CONSTANT} | bc -l | awk '{printf "%.12f", $0}')
            break
        fi
    done

    # check if number using regular expression
    if ! [[ ${EIGEN_VIRT_RESULT} =~ ${NUMBER_RE} ]]
    then
       echo "${FILE_NAME} Error: EIGEN_VIRT_RESULT is not a number"
       exit 1
    fi
}

FILE_PREFIX=${1}
FILE_POSTFIX=1
FILE_EXT=${2}

if [[ -n ${FILE_PREFIX} ]]
then
  echo "File prefix : ${FILE_PREFIX}"
else
  echo "Error: Please input file prefix"
  exit 1
fi

OUTPUT_FILE_NAME="result-$(date +"%Y%m%d-%H%M%S").txt"
echo ${OUTPUT_FILE_NAME}

if [[ -n ${FILE_EXT} ]]
then
  echo "File extension : ${FILE_EXT}"
else
  echo "Error: Please input file extension"
  exit 1
fi

echo ""
echo "SCF_Done:__E(RPW91-PW91)|Excited_State___1:______Singlet-A|Alpha__occ._eigenvalues|Alpha_virt._eigenvalues" >> ${OUTPUT_FILE_NAME}

FILE_NAME=${FILE_PREFIX}${FILE_POSTFIX}.${FILE_EXT}
while [[ -f "${FILE_NAME}" ]]
do
  get_values
  ((FILE_POSTFIX++))
  FILE_NAME=${FILE_PREFIX}${FILE_POSTFIX}.txt
done
