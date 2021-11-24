#!/bin/bash

while getopts ":p:" opt; do
  case $opt in
    p) p_out="$OPTARG"
    ;;
    \?) echo "Invalid option -$OPTARG" >&2
    exit 1
    ;;
  esac

  case $OPTARG in
    -*) echo "Option $opt needs a valid argument"
    exit 1
    ;;
  esac
done

make_script_file() {
  echo -e "#!/bin/sh\n" >> $1.sh
  echo -e "#PBS -V" >> $1.sh
  echo -e "#PBS -N "file_name"" >> $1.sh
  echo -e "#PBS -q normal" >> $1.sh
  echo -e "#PBS -l select=1:ncpus=64:mpiprocs=1:ompthreads=64" >> $1.sh
  echo -e "#PBS -l walltime=08:00:00" >> $1.sh
  echo -e "#PBS -A gaussian\n" >> $1.sh
  echo -e "cd \$PBS_O_WORKDIR\n" >> $1.sh
  echo -e "module purge" >> $1.sh
  echo -e "module load gaussian/g16.a03\n" >> $1.sh
  echo -e "export GAUSS_PDEF=\$NCPUS" >> $1.sh
  echo -e "g16 $1.gjf\n" >> $1.sh
  echo -e "exit 0" >> $1.sh
}

create_matching_file() {
  if [ -e "$1.sh" ]; then
    echo -e "Remove previous $1.sh" 
    rm $1.sh
  fi
  echo "Try to make file $1.sh"
  make_script_file $1
  if [ -e "$1.sh" ]; then
    echo -e "$1.sh created\n"
  fi
}

if [ -z $p_out ]; then
  echo "Target direcotry is not being specified, so set target directory using pwd"
  FILE_PATH=$(pwd)
else
  echo "Target direcotry is being specified"
  FILE_PATH=$p_out
fi

echo $FILE_PATH

for file in "$FILE_PATH"/* 
do
  if [ -f "$file" ]; then
    if [ "${file: -4}" == ".gjf" ]; then
      file_name_with_ext="${file##*/}"
      file_name=$(echo "$file_name_with_ext" | cut -f 1 -d '.')
      create_matching_file $file_name
    fi
  fi
done

echo -e "Process is done"