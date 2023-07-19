package com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.students

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mbah1.jephthah.fieldservicereporter.R
import com.mbah1.jephthah.fieldservicereporter.features.students.domain.model.StudentModel
import com.mbah1.jephthah.fieldservicereporter.features.students.presentaion.components.StudentInfoRow
import com.mbah1.jephthah.fieldservicereporter.ui.components.ServiceReportFAB
import com.mbah1.jephthah.fieldservicereporter.ui.navigation.components.ServiceReportTopAppBar

@Composable
fun StudentDetailsScreen(
    studentDetails: StudentModel?,
    navigateToAddEditStudent:(studentId: String) -> Unit,
    navigateUp: () -> Unit,
    widthSizeClass: WindowWidthSizeClass
) {


    Scaffold (

        floatingActionButton = {
            ServiceReportFAB(icon = Icons.Default.Edit){
                navigateToAddEditStudent(studentDetails?.id.toString())
            }
        },

        ){ contentPadding->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp),
                shape = RoundedCornerShape(50)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.student_photo_placeholder),
                    contentDescription = "Image place holder",
                    contentScale = ContentScale.Crop
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if(studentDetails != null){
                    item {
                        StudentInfoRow(label = "Name : ", text = studentDetails.studentName)
                        StudentInfoRow(label = "Address : ", text = studentDetails.address)
                        StudentInfoRow(label = "Phone Number : ", text = studentDetails.phoneNumber)
                        StudentInfoRow(label = "Email : ", text = studentDetails.email)
                        StudentInfoRow(label = "Book Studying : ", text = studentDetails.bookStudying)
                        StudentInfoRow(label = "Lesson Under Consideration : ", text = studentDetails.lessonUnderConsideration)
                        StudentInfoRow(label = "Time Of Visit : ", text = studentDetails.timeOfVisit)
                        StudentInfoRow(label = "Question To Consider : ", text = studentDetails.questionToConsider)


                    }
                }

            }
        }

    }

}


