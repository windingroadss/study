#!/bin/sh

#PBS -V
#PBS -N file_name
#PBS -q normal
#PBS -l select=1:ncpus=64:mpiprocs=1:ompthreads=64
#PBS -l walltime=08:00:00
#PBS -A gaussian

cd $PBS_O_WORKDIR

module purge
module load gaussian/g16.a03

export GAUSS_PDEF=$NCPUS
g16 file_test_03.gjf

exit 0
